package com.example.flight;


import com.example.flight.domain.model.Flight;
import com.example.flight.domain.model.FlightId;
import com.example.flight.domain.repository.FlightRepository;
import com.example.sharekernel.domain.financial.Currency;
import com.example.sharekernel.domain.financial.Money;
import com.example.sharekernel.domain.time.Time;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.TimeZone;

@Component
public class DataGenerator {
    private final FlightRepository flightRepository;


    public DataGenerator(FlightRepository flightRepository) {
        this.flightRepository = flightRepository;
    }

    @PostConstruct
    @Transactional
    public void generateData(){
        if(flightRepository.findAll().size() == 0){
            var flights = new ArrayList<Flight>();
            Time t = new Time(LocalTime.parse("10:00"),LocalTime.parse("12:00"), TimeZone.getTimeZone("Europe/Copenhagen"));
            FlightId id1 = new FlightId("1");
            FlightId id2 = new FlightId("2");
            FlightId id3 = new FlightId("3");
            flights.add(new Flight(id1,new Money(Currency.EUR,1000),"Skopje","Milano",100,100,t));
            flights.add(new Flight(id2,new Money(Currency.EUR,1500),"Skopje","Berlin",100,100,t));
            flights.add(new Flight(id3,new Money(Currency.EUR,1400),"Skopje","London",100,100,t));
            flightRepository.saveAll(flights);
        }
    }
}
