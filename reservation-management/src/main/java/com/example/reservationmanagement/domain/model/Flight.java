package com.example.reservationmanagement.domain.model;

import com.example.sharekernel.domain.financial.Money;
import lombok.Getter;

@Getter
public class Flight {

    private String cityFrom;
    private String cityTo;
    private FlightId flightId;
    private Money price;
    private int freeSets;
}
