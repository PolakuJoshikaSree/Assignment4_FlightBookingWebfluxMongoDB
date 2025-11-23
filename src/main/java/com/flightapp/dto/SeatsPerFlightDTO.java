package com.flightapp.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SeatsPerFlightDTO {

    // Used to show how many seats were booked for each flight.
    private String flightId;
    private long totalSeatsBooked;
}
