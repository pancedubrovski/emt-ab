package com.example.flight.integreation;

import com.example.sharekernel.infra.eventlog.RemoteEventTranslator;
import com.example.sharekernel.infra.eventlog.StoredDomainEvent;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Optional;

public class ReservationItemCancelEventTranslator implements RemoteEventTranslator {

    private final ObjectMapper objectMapper;

    public ReservationItemCancelEventTranslator(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public boolean supports(StoredDomainEvent storedDomainEvent) {
        return storedDomainEvent.domainEventClassName().equals("com.example.reservationmanagement.domain.events.ReservationItemDelete");
    }

    @Override
    public Optional<Object> translate(StoredDomainEvent remoteEvent) {
        return Optional.of(remoteEvent.toDomainEvent(objectMapper,ReservationCancelEvent.class));
    }
}
