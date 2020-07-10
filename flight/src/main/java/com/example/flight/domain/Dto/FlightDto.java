package com.example.flight.domain.Dto;

import com.example.sharekernel.domain.financial.Currency;
import lombok.Getter;

import java.time.LocalTime;

@Getter
public class FlightDto {
    private String id;
    private float price;
    private Currency currency;
    private String  formCity;
    private String  toCity;
    private LocalTime start;
    private LocalTime end;
    private int freeSets;
    private int totalSets;
}
