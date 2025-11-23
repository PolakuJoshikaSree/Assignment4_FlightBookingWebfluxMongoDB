package com.flightapp.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@NoArgsConstructor
public class FlightDTO {

    private Long id;
    private String flightNumber;
    private String fromPlace;
    private String toPlace;
    private LocalDate flightDate;
    private LocalTime departureTime;
    private LocalTime arrivalTime;
    private double baggageLimitKg;
    private int totalSeats;
    private int bookedSeats;
    private double price;
    private String mealTypeAvailable;
    private Long airlineId;
}
