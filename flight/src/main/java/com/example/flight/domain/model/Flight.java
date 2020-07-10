package com.example.flight.domain.model;

import com.example.sharekernel.domain.base.AbstractEntity;
import com.example.sharekernel.domain.financial.Money;
import com.example.sharekernel.domain.time.Time;
import lombok.Getter;

import javax.persistence.*;


@Entity
@Getter
//@Where(clause = "deleted=false")
@Table(name = "flights")
public class Flight extends AbstractEntity<FlightId> {

//    @EmbeddedId
//    private FlightId flightId;
    @Version
    private Long version;


    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name="amount",column = @Column(name= "amount")),
            @AttributeOverride(name="currency",column = @Column(name= "currency"))
    })
    private Money price;
    private String fromCity;
    private String toCity;
    private int freeSets;
    private int totalSets;
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name="timeTo",column = @Column(name= "time_to")),
            @AttributeOverride(name="timeFrom",column = @Column(name= "time_from")),
            @AttributeOverride(name="timeZone",column = @Column(name= "time_zone"))
    })
    private Time time;
    private boolean deleted = false;

    public Flight(FlightId flightId,Money money,String fromCity,String toCity,int freeSets,int totalSets,Time time){
        super(flightId);
      //  this.flightId = flightId;
        this.fromCity = fromCity;
        this.toCity = toCity;
        this.freeSets = freeSets;
        this.totalSets = totalSets;
        this.price = money;
        this.time = time;
    }

    public Flight(){

    }


    public void subtractSets(int num){
        if (num>this.freeSets) {
            throw new RuntimeException("unsupported quantity");
        }
        this.freeSets-=num;
    }
    public void cancelReservationSetSets(int num){

        this.freeSets+=num;
    }

    public Flight(FlightId id) {
        super(id);
    }
}
