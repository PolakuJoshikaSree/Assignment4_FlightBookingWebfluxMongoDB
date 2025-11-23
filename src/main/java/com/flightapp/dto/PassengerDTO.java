package com.flightapp.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PassengerDTO {

    private Long id;
    private String passengerName;
    private int age;
    private String gender;
    private String mealPreference;
    private double luggageWeight;
}
