package com.flightapp.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PassengerCountDTO {
    private String flightId;
    private int totalPassengers;
}
