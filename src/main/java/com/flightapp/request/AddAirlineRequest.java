package com.flightapp.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AddAirlineRequest {

    @NotBlank(message = "Airline name is required")
    private String airlineName;

    @NotBlank(message = "Airline code is required")
    private String airlineCode;

    private String country;
}
