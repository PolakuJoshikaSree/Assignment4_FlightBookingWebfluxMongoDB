package com.flightapp.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@Document(collection = "bookings")
public class Booking {

    @Id
    private String id;

    private String pnr;
    private String flightId;
    private String email;
    private String primaryPassenger;
    private int seatsBooked;
    private LocalDateTime bookingTime;
    private String status;
}
