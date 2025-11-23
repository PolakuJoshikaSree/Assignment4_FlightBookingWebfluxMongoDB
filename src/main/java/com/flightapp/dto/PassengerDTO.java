package com.flightapp.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PassengerDTO {

    private Long id;
    private String passengerName;
    private int age;
    private String gender;
    private String mealPreference;
    private double luggageWeight;
}
