package com.example.reservationmanagement.Port.Rest;

import com.example.reservationmanagement.application.ReservationService;
import com.example.reservationmanagement.domain.dto.ReservationDto;
import com.example.reservationmanagement.domain.model.ReservationId;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/reservation")
public class ReservationController {

    private final ReservationService reservationService;

    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @PostMapping
    public ReservationId addReservation(@RequestBody ReservationDto reservationDto){
        System.out.println(reservationDto.getFlights());
       return reservationService.creatReservation(reservationDto);
    }

    @GetMapping("/{reservationId}/flights/{reservationItemId}")
    public void cancelReservation(@PathVariable("reservationId") String reservationId,
                                  @PathVariable("reservationItemId") String reservationItemId){
        this.reservationService.cancelReservationItem(reservationId,reservationItemId);
    }

}
