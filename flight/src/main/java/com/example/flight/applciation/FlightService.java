package com.example.flight.applciation;

import com.example.flight.domain.Dto.FlightDto;
import com.example.flight.domain.model.Flight;
import com.example.flight.domain.model.FlightId;
import com.example.flight.domain.repository.FlightRepository;
import com.example.flight.integreation.ReservationCancelEvent;
import com.example.flight.integreation.ReservationItemAddEvent;
import com.example.sharekernel.domain.financial.Currency;
import com.example.sharekernel.domain.financial.Money;
import com.example.sharekernel.domain.time.Time;
import com.sun.istack.NotNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.TimeZone;

@Service
@Transactional
public class FlightService {

    private final FlightRepository flightRepository;

    public FlightService(FlightRepository flightRepository) {
        this.flightRepository = flightRepository;
    }


    @NotNull
    public Optional<Flight> findFlightById(@NotNull FlightId flightId){
        Objects.requireNonNull(flightId,"Flight id must not be null");
        return this.flightRepository.findById(flightId);
    }

    public List<Flight> findAllFlights(){
        return this.flightRepository.findAll();
    }

    public Flight addFlight(FlightDto flightDto){
        FlightId flightId1 =  new FlightId(flightDto.getId());
        Money m = new Money(Currency.EUR,flightDto.getPrice());
        Time t = new Time(flightDto.getStart(),flightDto.getEnd(), TimeZone.getTimeZone("Europe/Copenhagen"));
        return this.flightRepository.save(new Flight(flightId1,m,flightDto.getFormCity(),flightDto.getToCity(),flightDto.getFreeSets(),flightDto.getTotalSets(),t));
    }

    @TransactionalEventListener(phase = TransactionPhase.BEFORE_COMMIT)
    public void onReservationCreateEvent(ReservationItemAddEvent event){
        Flight f = flightRepository.findById(event.getFlightId()).orElseThrow(RuntimeException::new);
        f.subtractSets(event.getSets());
        flightRepository.save(f);
    }

    @TransactionalEventListener(phase = TransactionPhase.BEFORE_COMMIT)
    public void onReservationItemCancelEvent(ReservationCancelEvent event){
        Flight f = flightRepository.findById(event.getFlightId()).orElseThrow(RuntimeException::new);
        f.cancelReservationSetSets(event.getSets());
        flightRepository.save(f);

    }
}
