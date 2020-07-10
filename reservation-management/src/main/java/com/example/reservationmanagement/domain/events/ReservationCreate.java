package com.example.reservationmanagement.domain.events;

import com.example.reservationmanagement.domain.model.ReservationId;
import com.example.sharekernel.domain.base.DomainEvent;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.sun.istack.NotNull;
import lombok.NonNull;

import java.time.Instant;
import java.util.Objects;

public class ReservationCreate implements DomainEvent {


    @JsonProperty("reservationId")
    private final ReservationId reservationId;

    @JsonProperty("occurredOn")
    private final Instant occurredOn;



    @JsonCreator
    public ReservationCreate(@JsonProperty("reservationId") @NotNull ReservationId reservationId,
                             @JsonProperty("occurredOn") @NotNull Instant occurredOn) {
        this.reservationId = Objects.requireNonNull(reservationId,"reservation id must not be null");
        this.occurredOn = Objects.requireNonNull(occurredOn,"occurred must not be null");
    }

    @NonNull
    public ReservationId orderId() {
        return reservationId;
    }
    @Override
    public @NonNull Instant occurredOn() {
        return occurredOn;
    }
}
