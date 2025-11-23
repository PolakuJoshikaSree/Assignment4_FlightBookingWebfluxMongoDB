package com.flightapp.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FlightCountPerAirlineDTO {
    private String airlineCode;
    private String airlineName;
    private int totalFlights;
}
