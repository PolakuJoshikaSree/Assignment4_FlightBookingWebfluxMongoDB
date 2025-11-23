package com.flightapp.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FlightSeatStatsDTO {

    // Used for showing total, booked, and available seats for flights.
    private String flightNumber;
    private String fromPlace;
    private String toPlace;
    private String flightDate;
    private int totalSeats;
    private int totalSeatsBooked;
    private int remainingSeats;
}
