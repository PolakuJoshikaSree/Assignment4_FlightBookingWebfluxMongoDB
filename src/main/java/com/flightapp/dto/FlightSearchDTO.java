package com.flightapp.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class FlightSearchDTO {

    private String from;
    private String to;
    private LocalDate date;
}
