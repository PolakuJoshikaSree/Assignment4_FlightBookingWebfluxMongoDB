package com.flightapp.model;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class BookingTest {

    @Test
    void testBookingLifecycle() {
        Booking b = new Booking();

        LocalDateTime now = LocalDateTime.of(2025, 11, 30, 10, 30);

        b.setId("B1");
        b.setPnr("PNR123");
        b.setFlightId("F1");
        b.setEmail("test@gmail.com");
        b.setPrimaryPassenger("John");
        b.setSeatsBooked(2);
        b.setBookingTime(now);
        b.setStatus("CONFIRMED");

        assertEquals("B1", b.getId());
        assertEquals("PNR123", b.getPnr());
        assertEquals("F1", b.getFlightId());
        assertEquals("test@gmail.com", b.getEmail());
        assertEquals("John", b.getPrimaryPassenger());
        assertEquals(2, b.getSeatsBooked());
        assertEquals(now, b.getBookingTime());
        assertEquals("CONFIRMED", b.getStatus());

        assertNotNull(b.toString());
        assertTrue(b.toString().contains("PNR"));
    }

    @Test
    void testNullValues() {
        Booking b = new Booking();

        b.setPnr(null);
        b.setEmail(null);
        b.setStatus(null);

        assertNull(b.getPnr());
        assertNull(b.getEmail());
        assertNull(b.getStatus());

        assertNotNull(b.toString());
    }
}
