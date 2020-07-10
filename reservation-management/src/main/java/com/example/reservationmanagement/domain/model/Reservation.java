package com.example.reservationmanagement.domain.model;

import com.example.sharekernel.domain.base.AbstractEntity;
import com.example.sharekernel.domain.base.DomainObjectId;
import com.example.sharekernel.domain.financial.Currency;
import com.example.sharekernel.domain.financial.Money;
import com.example.sharekernel.domain.identity.FullName;
import lombok.Getter;

import javax.persistence.*;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Stream;

@Getter
@Entity
public class Reservation extends AbstractEntity<ReservationId> {


//    @EmbeddedId
//    private ReservationId reservationId;
    @Version
    private Long version;

    @Column(name= "booked",nullable = false)
    private Instant booked;

    private Currency currency;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private Set<ReservationItem> items;

    @Embedded
    private FullName person;

    public Reservation(Instant booked,Currency currency,FullName person){
        super(DomainObjectId.randomId(ReservationId.class));
        this.booked = booked;
        this.currency = currency;
        this.person = person;
        this.items = new HashSet<>();
    }

    public Reservation(){}
    public ReservationItem addFlight(ReservationItem reservationItem){

        items.add(reservationItem);

        return reservationItem;
    }

    public void  removeReservationFlight(ReservationItem flight){
   //      items.remove(flight);
    }

    public Money total(){
        return items.stream().map(ReservationItem::subtotal)
                .reduce(new Money(currency,0),Money::add);
    }
    public Stream<ReservationItem> getItems(){
        return this.items.stream();
    }

    public Reservation(ReservationId id) {
        super(id);
    }
}
