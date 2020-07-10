package com.example.reservationmanagement.application;

import com.example.reservationmanagement.domain.dto.FlightRdto;
import com.example.reservationmanagement.domain.dto.ReservationDto;
import com.example.reservationmanagement.domain.events.ReservationCreate;
import com.example.reservationmanagement.domain.events.ReservationItemAdd;
import com.example.reservationmanagement.domain.events.ReservationItemDelete;
import com.example.reservationmanagement.domain.model.*;
import com.example.reservationmanagement.domain.repository.ReservationItemRespository;
import com.example.reservationmanagement.domain.repository.ReservationRepository;
import com.example.sharekernel.domain.financial.Currency;
import com.example.sharekernel.domain.financial.Money;
import com.example.sharekernel.domain.identity.FullName;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.constraints.NotNull;
import java.time.Instant;
import java.util.Objects;
import java.util.Optional;


@Service
@Transactional
public class ReservationService {

    private final ReservationRepository reservationRepository;
    private final ApplicationEventPublisher applicationEventPublisher;
    private final ReservationItemRespository reservationItemRespository;

    private Flights flights;

    public ReservationService(ReservationRepository reservationRepository, ApplicationEventPublisher applicationEventPublisher, ReservationItemRespository reservationItemRespository, Flights flights) {
        this.reservationRepository = reservationRepository;
        this.applicationEventPublisher = applicationEventPublisher;
        this.reservationItemRespository = reservationItemRespository;


        this.flights = flights;


    }

    @NonNull
    public Optional<Reservation> getReservation(@NonNull ReservationId reservationID){
        Objects.requireNonNull(reservationID,"id must not be null");
        return this.reservationRepository.findById(reservationID);

    }


    public void cancelReservationItem(@NotNull String  reservationId, @NonNull String reservationItemId){
        Reservation reservation = reservationRepository.findById(new ReservationId(reservationId)).orElseThrow(RuntimeException::new);
        ReservationItem reservationItem = reservationItemRespository.findById(new ReservationItemId(reservationItemId)).orElseThrow(RuntimeException::new);
      //  reservationItem.setDelete(true);
        reservation.removeReservationFlight(reservationItem);
        applicationEventPublisher.publishEvent(new ReservationItemDelete(reservation.id(),reservationItem.getId(),reservationItem.getFlightId(),reservationItem.getSets(),Instant.now()));
        reservationRepository.save(reservation);
        reservationItemRespository.save(reservationItem);

    }
    public ReservationId creatReservation(@NonNull ReservationDto reservation){

        Reservation newReservation = reservationRepository.saveAndFlush(toDomainModel(reservation));



        for ( FlightRdto i : reservation.getFlights()) {
                ReservationItem reservationItem = reservationItemRespository.save(new ReservationItem(i.getReservationItemId(),new FlightId(i.getFlightId()),i.getSets(),new Money(Currency.EUR,i.getPrice())));
                newReservation.addFlight(reservationItem);

        }

        reservationRepository.save(newReservation);
        applicationEventPublisher.publishEvent(new ReservationCreate(newReservation.id(),newReservation.getBooked()));
        newReservation.getItems().forEach(i->
                applicationEventPublisher
                        .publishEvent(new ReservationItemAdd(
                                newReservation.id(),
                                i.getId(),
                                i.getFlightId(),
                                i.getSets(),
                                Instant.now())));
        return newReservation.id();
    }


    @NonNull
    private Reservation toDomainModel(@NonNull ReservationDto reservationForm) {
        Reservation reservation = new Reservation(Instant.now(), Currency.EUR,new FullName(reservationForm.getFirstName(),reservationForm.getLastName(),reservationForm.getPassport()));

        return reservation;
    }

}
