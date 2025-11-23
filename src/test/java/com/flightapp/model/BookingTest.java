package com.flightapp.model;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class BookingTest {

    @Test
    void testBookingModel() {
        Booking b = new Booking();
        b.setId("10");
        b.setPnr("PNR123");
        b.setFlightId("FL1");
        b.setEmail("test@mail.com");
        b.setPrimaryPassenger("John");
        b.setSeatsBooked(2);
        b.setBookingTime(LocalDateTime.now());
        b.setStatus("CONFIRMED");

        assertEquals("10", b.getId());
        assertEquals("PNR123", b.getPnr());
        assertEquals("FL1", b.getFlightId());
        assertEquals("test@mail.com", b.getEmail());
        assertEquals("John", b.getPrimaryPassenger());
        assertEquals(2, b.getSeatsBooked());
        assertEquals("CONFIRMED", b.getStatus());
    }
}
