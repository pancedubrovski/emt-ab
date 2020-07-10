package com.example.reservationmanagement.domain.model;

import com.example.sharekernel.domain.base.AbstractEntity;
import com.example.sharekernel.domain.financial.Money;
import lombok.Getter;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;

@Entity
@Getter
public class ReservationItem extends AbstractEntity<ReservationItemId> {

    @Embedded
    @AttributeOverride(name="id", column=@Column(name="flight_id",nullable = false))
    private FlightId flightId;

    private int sets;

  //  private boolean delete=false;

  //  public void setDelete(boolean f){
  //      this.delete =delete;
   // }

    @Embedded
    private Money price;
    public ReservationItem(){

    }

    public ReservationItem(ReservationItemId id) {
        super(id);
    }
    public void setSets(int q){
        this.sets = q;
    }
    public ReservationItem(String id,FlightId flightId,int sets,Money price) {
        super(new ReservationItemId(id));
        this.flightId = flightId;
        this.sets = sets;
        setPrice(price);
    }

    public void setPrice(Money price){
        this.price = price;
    }

    public Money subtotal(){
        return price.multiply(sets);
    }




}
