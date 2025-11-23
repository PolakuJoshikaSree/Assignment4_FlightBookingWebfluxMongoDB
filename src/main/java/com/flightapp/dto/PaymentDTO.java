package com.flightapp.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class PaymentDTO {

    private Long id;
    private double amount;
    private String paymentMode;
    private String status;
    private LocalDateTime paymentTime;
    private Long bookingId;
}
