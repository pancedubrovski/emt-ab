package com.example.flight.domain.model;

import com.example.sharekernel.domain.base.DomainObjectId;
import com.fasterxml.jackson.annotation.JsonCreator;

public class ReservationId extends DomainObjectId {

    @JsonCreator
    public ReservationId(String id) {
        super(id);

    }
}
