package com.example.flight.integreation;


import com.example.sharekernel.infra.eventlog.RemoteEventTranslator;
import com.example.sharekernel.infra.eventlog.StoredDomainEvent;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ReservationItemAddEventTranslator implements RemoteEventTranslator {

    private final ObjectMapper objectMapper;

    public ReservationItemAddEventTranslator(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public boolean supports(StoredDomainEvent storedDomainEvent) {
        return storedDomainEvent.domainEventClassName().equals("com.example.reservationmanagement.domain.events.ReservationItemAdd");
    }

    @Override
    public Optional<Object> translate(StoredDomainEvent remoteEvent) {
        return Optional.of(remoteEvent.toDomainEvent(objectMapper, ReservationItemAddEvent.class));
    }
}
