package com.flightapp.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FlightCountPerAirlineDTO {

    // Used to return how many flights each airline has.
    private String airlineCode;
    private String airlineName;
    private int totalFlights;
}
