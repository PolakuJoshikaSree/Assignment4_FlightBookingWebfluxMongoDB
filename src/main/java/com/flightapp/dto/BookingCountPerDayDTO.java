package com.flightapp.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookingCountPerDayDTO {
    private String date;
    private int totalBookings;
}
