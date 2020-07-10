package com.example.flight.integreation;

import com.example.flight.domain.model.FlightId;
import com.example.flight.domain.model.ReservationItemId;
import com.example.reservationmanagement.domain.model.ReservationId;
import com.example.sharekernel.domain.base.DomainEvent;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NonNull;

import java.time.Instant;

@Getter
public class ReservationCancelEvent implements DomainEvent {


    @JsonProperty("reservationId")
    private final ReservationId reservationId;

    @JsonProperty("reservationItemId")
    private final ReservationItemId reservationItemId;

    @JsonProperty("flightId")
    private final FlightId flightId;

    @JsonProperty("sets")
    private final int sets;

    @JsonProperty("occurredOn")
    private final Instant occurredOn;

    @JsonCreator
    public ReservationCancelEvent(ReservationId reservationId, ReservationItemId reservationItemId, FlightId flightId, int sets,Instant occurredOn) {
        this.reservationId = reservationId;
        this.reservationItemId = reservationItemId;
        this.flightId = flightId;
        this.sets = sets;
        this.occurredOn = occurredOn;
    }

    @Override
    public @NonNull Instant occurredOn() {
        return occurredOn;
    }
}
