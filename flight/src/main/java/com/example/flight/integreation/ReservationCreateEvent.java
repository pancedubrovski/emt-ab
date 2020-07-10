package com.example.flight.integreation;

import com.example.flight.domain.model.ReservationId;
import com.example.sharekernel.domain.base.DomainEvent;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.NonNull;

import java.time.Instant;
import java.util.Objects;

public class ReservationCreateEvent implements DomainEvent {


    @JsonProperty("reservationId")
    private final ReservationId reservationId;

    @JsonProperty("occurredOn")
    private final Instant occurred;



    @JsonCreator
    public ReservationCreateEvent(ReservationId reservationId,Instant occurred) {
        this.reservationId = Objects.requireNonNull(reservationId,"reservation id must not be null");
        this.occurred = Objects.requireNonNull(occurred,"occurred must not be null");

    }

    @Override
    public @NonNull Instant occurredOn() {
        return this.occurred;
    }
}
