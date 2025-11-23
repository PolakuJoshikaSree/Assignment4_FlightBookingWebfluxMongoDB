package com.flightapp.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class FlightSearchRequest {

    @NotBlank
    private String fromPlace;

    @NotBlank
    private String toPlace;

    @NotBlank
    private String flightDate; // yyyy-MM-dd
}
