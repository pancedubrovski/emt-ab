package com.example.flight.domain.model;

import com.example.sharekernel.domain.base.DomainObjectId;
import com.fasterxml.jackson.annotation.JsonCreator;

public class ReservationItemId extends DomainObjectId {
    private String id;


    private ReservationItemId() {
        super(DomainObjectId.randomId(ReservationItemId.class).toString());
    }

    @JsonCreator
    public ReservationItemId(String uuid) {
        super(uuid);
        this.id=uuid;
    }
}
