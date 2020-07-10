package com.example.reservationmanagement.domain.dto;

import lombok.Data;

import java.util.List;

@Data
public class ReservationDto {

    private String firstName;
    private String lastName;
    private String passport;
    List<FlightRdto> flights;

}


