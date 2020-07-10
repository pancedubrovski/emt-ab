package com.example.flight.domain.repository;

import com.example.flight.domain.model.Flight;
import com.example.flight.domain.model.FlightId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FlightRepository extends JpaRepository<Flight, FlightId> {
}
