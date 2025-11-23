package com.flightapp.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AddFlightRequest {

    @NotBlank
    private String flightNumber;

    @NotBlank
    private String fromPlace;

    @NotBlank
    private String toPlace;

    @NotBlank
    private String flightDate; // yyyy-MM-dd

    @NotBlank
    private String departureTime; // HH:mm

    @NotBlank
    private String arrivalTime; // HH:mm

    @NotNull
    private Integer totalSeats;

    @NotNull
    private Double price;

    @NotNull
    private Integer baggageLimitKg;

    @NotBlank
    private String airlineCode;
}
