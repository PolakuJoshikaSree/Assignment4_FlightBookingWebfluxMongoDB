package com.flightapp.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class BookingRequest {

    @NotBlank
    private String email;

    @NotBlank
    private String primaryPassenger;

    @Min(1)
    private int seats;

    @Valid
    private List<PassengerRequest> passengers;
}
