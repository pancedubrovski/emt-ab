package com.example.flight.port.rest;

import com.example.flight.applciation.FlightService;
import com.example.flight.domain.Dto.FlightDto;
import com.example.flight.domain.model.Flight;
import com.example.flight.domain.model.FlightId;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "api/flight",produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
public class FlightController {

    private final FlightService flightService;

    public FlightController(FlightService flightService) {
        this.flightService = flightService;
    }


    @GetMapping
    public List<Flight> getAllFlights(){
        return this.flightService.findAllFlights();
    }

    @PostMapping
    public Flight addFlight(@RequestBody FlightDto flight){
        return this.flightService.addFlight(flight);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Flight> getFlightById(@PathVariable("id") String id) {
        return this.flightService.findFlightById(new FlightId(id))
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }


}
