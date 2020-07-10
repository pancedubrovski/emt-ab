package com.example.reservationmanagement.domain.repository;

import com.example.reservationmanagement.domain.model.Reservation;
import com.example.reservationmanagement.domain.model.ReservationId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservationRepository extends JpaRepository<Reservation, ReservationId> {

}
