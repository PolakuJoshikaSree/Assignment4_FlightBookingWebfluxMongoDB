package com.flightapp.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@Document(collection = "payments")
public class Payment {

    @Id
    private String id;

    private double amount;
    private String paymentMode;
    private LocalDateTime paymentTime;
    private String status;
    private String bookingId;
}
