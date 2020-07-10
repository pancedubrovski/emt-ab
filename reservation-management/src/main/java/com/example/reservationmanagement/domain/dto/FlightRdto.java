package com.example.reservationmanagement.domain.dto;

import lombok.Data;
@Data
public class FlightRdto {
    private String reservationItemId;
    private String flightId;
    private int sets;
    private float price;
}
