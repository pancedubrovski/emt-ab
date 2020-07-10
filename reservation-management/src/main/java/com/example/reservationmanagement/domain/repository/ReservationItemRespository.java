package com.example.reservationmanagement.domain.repository;

import com.example.reservationmanagement.domain.model.ReservationItem;
import com.example.reservationmanagement.domain.model.ReservationItemId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservationItemRespository  extends JpaRepository<ReservationItem, ReservationItemId> {
}
