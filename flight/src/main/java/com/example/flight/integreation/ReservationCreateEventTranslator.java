package com.example.flight.integreation;

import com.example.sharekernel.infra.eventlog.RemoteEventTranslator;
import com.example.sharekernel.infra.eventlog.StoredDomainEvent;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ReservationCreateEventTranslator implements RemoteEventTranslator {

    private final ObjectMapper objectMapper;

    public ReservationCreateEventTranslator(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public boolean supports(StoredDomainEvent storeDomainEvent) {
        return storeDomainEvent.domainEventClassName().equals("com.example.reservationmanagement.domain.events.ReservationCreate");
    }

    @Override
    public Optional<Object> translate(StoredDomainEvent remoteEvent) {
        return Optional.of(remoteEvent.toDomainEvent(objectMapper,ReservationCreateEvent.class));
    }
}
