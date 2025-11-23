package com.flightapp.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FlightRevenueDTO {
    private String flightId;
    private double totalRevenue;
}
