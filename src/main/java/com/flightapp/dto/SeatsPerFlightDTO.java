package com.flightapp.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SeatsPerFlightDTO {

    private String flightId;
    private long totalSeatsBooked;
}
