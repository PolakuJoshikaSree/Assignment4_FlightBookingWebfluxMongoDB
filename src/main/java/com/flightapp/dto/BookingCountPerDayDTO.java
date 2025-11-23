package com.flightapp.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookingCountPerDayDTO {

    // Used for aggregation results showing daily booking count.
    private String date;
    private int totalBookings;
}
