package com.example.flight.domain.model;


import com.example.sharekernel.domain.base.DomainObjectId;
import com.fasterxml.jackson.annotation.JsonCreator;

import javax.persistence.Embeddable;


@Embeddable
public class FlightId extends DomainObjectId {
    protected FlightId() {
        super(DomainObjectId.randomId(FlightId.class).toString());
    }



    @JsonCreator
    public FlightId(String id) {
        super(id);
    }
}
