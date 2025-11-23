package com.flightapp.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@Document(collection = "flights")
public class Flight {

    @Id
    private String id;

    private String flightNumber;
    private String airlineCode;
    private String fromPlace;
    private String toPlace;
    private LocalDate flightDate;
    private LocalTime departureTime;
    private LocalTime arrivalTime;
    private int totalSeats;
    private int bookedSeats;
    private double price;
    private String mealTypeAvailable;
    private double baggageLimitKg;
}
