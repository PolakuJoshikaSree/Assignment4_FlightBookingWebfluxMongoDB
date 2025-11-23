package com.flightapp.model;

import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class BookingTest {

    @Test
    void testBookingModel() {
        Booking b = new Booking();
        LocalDateTime now = LocalDateTime.now();

        b.setId("B1");
        b.setPnr("PNR123");
        b.setFlightId("F1");
        b.setEmail("test@mail.com");
        b.setPrimaryPassenger("John");
        b.setSeatsBooked(2);
        b.setBookingTime(now);
        b.setStatus("CONFIRMED");

        assertEquals("B1", b.getId());
        assertEquals("PNR123", b.getPnr());
        assertEquals("F1", b.getFlightId());
        assertEquals("test@mail.com", b.getEmail());
        assertEquals("John", b.getPrimaryPassenger());
        assertEquals(2, b.getSeatsBooked());
        assertEquals(now, b.getBookingTime());
        assertEquals("CONFIRMED", b.getStatus());

        assertNotEquals(b, new Booking());
        assertNotNull(b.toString());
    }
}
