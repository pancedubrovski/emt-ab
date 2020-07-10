package com.example.reservationmanagement.application;

import com.example.reservationmanagement.domain.model.Flight;
import com.example.reservationmanagement.domain.model.FlightId;

import java.util.List;

public interface Flights {
    List<Flight> findAll();
    Flight findById(FlightId id);
}
