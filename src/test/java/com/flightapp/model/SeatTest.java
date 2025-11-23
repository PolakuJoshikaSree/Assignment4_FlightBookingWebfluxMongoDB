package com.flightapp.model;

import org.junit.jupiter.api.Test;

import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.*;

class SeatTest {

    @Test
    void testSeatLifecycle() {
        Seat s = new Seat();

        s.setId("S1");
        s.setSeatNumber("12A");
        s.setStatus("BOOKED");
        s.setFlightId("F1");

        assertEquals("S1", s.getId());
        assertEquals("12A", s.getSeatNumber());
        assertEquals("BOOKED", s.getStatus());
        assertEquals("F1", s.getFlightId());
        assertNotNull(s.toString());

        // FIX: set ALL fields on second seat
        Seat s2 = new Seat();
        s2.setId("S1");
        s2.setSeatNumber("12A");
        s2.setStatus("BOOKED");
        s2.setFlightId("F1");

        assertEquals(s, s2);  // now passes
        assertEquals(s.hashCode(), s2.hashCode());

        HashSet<Seat> set = new HashSet<>();
        set.add(s);
        assertTrue(set.contains(s));
    }

    @Test
    void testNullValues() {
        Seat s = new Seat();
        s.setSeatNumber(null);
        s.setStatus(null);

        assertNull(s.getSeatNumber());
        assertNull(s.getStatus());

        assertNotNull(s.toString());
    }
}
