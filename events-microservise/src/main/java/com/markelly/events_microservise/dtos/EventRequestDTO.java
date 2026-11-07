package com.markelly.events_microservise.dtos;

public record EventRequestDTO(int maxParticipants, int registeredParticipants, String date, String title, String description) {

}
