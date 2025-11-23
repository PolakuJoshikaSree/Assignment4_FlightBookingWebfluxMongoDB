package com.flightapp.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class SeatTest {

    @Test
    void testSeatModel() {
        Seat s = new Seat();

        s.setId("S1");
        s.setSeatNumber("12A");
        s.setStatus("BOOKED");
        s.setFlightId("F1");

        assertEquals("S1", s.getId());
        assertEquals("12A", s.getSeatNumber());
        assertEquals("BOOKED", s.getStatus());
        assertEquals("F1", s.getFlightId());
    }
}