package com.example.reservationmanagement.domain.model;


import com.example.sharekernel.domain.base.DomainObjectId;
import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;

import javax.persistence.Embeddable;

@Embeddable
@Getter
@EqualsAndHashCode
public class ReservationId extends DomainObjectId {
    public ReservationId(){super("");}

    @JsonCreator
    public ReservationId(@NonNull String id) {
        super(id);
    }
}
