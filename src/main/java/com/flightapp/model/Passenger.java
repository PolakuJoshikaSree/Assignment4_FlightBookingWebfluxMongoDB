package com.flightapp.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "passengers")
public class Passenger {

    @Id
    private String id;

    private String passengerName;
    private String gender;
    private int age;
    private String mealPreference;
    private double luggageWeight;
    private String bookingId;
}
