package com.flightapp.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AirlineDTO {

    private Long id;
    private String airlineName;
    private String airlineCode;
    private String country;
}
