package com.flightapp.request;

import com.flightapp.model.enums.Gender;
import com.flightapp.model.enums.MealType;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PassengerRequest {

    @NotBlank
    private String name;

    @NotNull
    private Gender gender;

    @Min(0)
    private int age;

    private MealType mealPreference;

    @Min(0)
    private double luggageWeight;
}
