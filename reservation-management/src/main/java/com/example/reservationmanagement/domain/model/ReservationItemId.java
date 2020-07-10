package com.example.reservationmanagement.domain.model;

import com.example.sharekernel.domain.base.DomainObjectId;
import com.fasterxml.jackson.annotation.JsonCreator;

import javax.persistence.Embeddable;

@Embeddable
public class ReservationItemId extends DomainObjectId {

    private String id;

    public ReservationItemId(){super("");}
    @JsonCreator
    public ReservationItemId(String id) {
        super(id);
        this.id=id;
    }
}
