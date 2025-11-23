package com.flightapp.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "seats")
public class Seat {

    @Id
    private String id;

    private String seatNumber;
    private String status;
    private String flightId;
}
