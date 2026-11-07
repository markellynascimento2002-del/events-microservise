package com.markelly.events_microservise.exceptions;

public class EventFullException extends RuntimeException {
    public EventFullException() {
        super("Evento cheio");
    }

    public EventFullException(String message) {
        super(message);
    }
}
