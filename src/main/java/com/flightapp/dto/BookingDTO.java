package com.flightapp.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class BookingDTO {

    private Long id;
    private String pnr;
    private String email;
    private String primaryPassenger;
    private int seatsBooked;
    private LocalDateTime bookingTime;
    private String status;
    private Long flightId;
    private List<PassengerDTO> passengers;
}
