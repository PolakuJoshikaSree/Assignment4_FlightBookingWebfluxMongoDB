package com.flightapp.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FlightSeatStatsDTO {
    private String flightNumber;
    private String fromPlace;
    private String toPlace;
    private String flightDate;
    private int totalSeats;
    private int totalSeatsBooked;
    private int remainingSeats;
}
