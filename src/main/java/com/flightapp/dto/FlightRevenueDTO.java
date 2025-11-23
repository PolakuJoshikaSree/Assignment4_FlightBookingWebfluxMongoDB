package com.flightapp.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FlightRevenueDTO {

    // DTO used for revenue aggregation results.
    private String flightId;
    private double totalRevenue;
}
