package com.markelly.events_microservise.services;

import com.markelly.events_microservise.domain.Event;
import com.markelly.events_microservise.domain.Subscription;
import com.markelly.events_microservise.dtos.EmailRequestDTO;
import com.markelly.events_microservise.dtos.EventRequestDTO;
import com.markelly.events_microservise.exceptions.EventFullException;
import com.markelly.events_microservise.exceptions.EventNotFoundException;
import com.markelly.events_microservise.repositories.EventRepository;
import com.markelly.events_microservise.repositories.SubscriptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class EventService {

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private SubscriptionRepository subscriptionRepository;

    @Autowired
    private EmailServiceClient emailServiceClient;

    public List<Event> getAllEvents() {
        return eventRepository.findAll();
    }

    public List<Event> getUpcomingEvents() {
        return eventRepository.findByDateAfterOrderByDate(LocalDateTime.now());
    }
    
    public Event createEvent(EventRequestDTO eventRequest) {
        Event newEvent = new Event(eventRequest);
        return eventRepository.save(newEvent);
    }

	private Boolean isEventFull(Event event){
        return event.getRegisteredParticipants() >= event.getMaxParticipants();
    }

    public void registerParticipant(String eventId, String participantEmail) {
				Event event = eventRepository.findById(eventId).orElseThrow(EventNotFoundException::new);


        if (event.getRegisteredParticipants() >= event.getMaxParticipants()) {
            Subscription subscription = new Subscription(null, event, participantEmail);
            subscriptionRepository.save(subscription);

            event.setRegisteredParticipants(event.getRegisteredParticipants() + 1);

            EmailRequestDTO emailRequest = new EmailRequestDTO(participantEmail, "Confirmação de Inscrição", "Você foi inscrito no evento com sucesso!");

            emailServiceClient.sendEmail(emailRequest);
        }else{
            throw new EventFullException("O evento está cheio. Não é possível registrar mais participantes.");
        }
    }
}