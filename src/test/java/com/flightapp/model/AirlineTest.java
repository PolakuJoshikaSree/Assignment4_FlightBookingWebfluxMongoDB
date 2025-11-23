package com.flightapp.model;

import org.junit.jupiter.api.Test;

import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.*;

class AirlineTest {

    @Test
    void testAirlineFullObjectLifecycle() {
        Airline airline = new Airline();

        airline.setId("A1");
        airline.setAirlineName("IndiGo");
        airline.setAirlineCode("6E");
        airline.setCountry("India");

        // Basic getter checks
        assertEquals("A1", airline.getId());
        assertEquals("IndiGo", airline.getAirlineName());
        assertEquals("6E", airline.getAirlineCode());
        assertEquals("India", airline.getCountry());

        // toString should not be null or empty
        assertNotNull(airline.toString());
        assertTrue(airline.toString().contains("IndiGo"));

        // hashCode should be stable
        int hash1 = airline.hashCode();
        int hash2 = airline.hashCode();
        assertEquals(hash1, hash2);

        // equals check
        Airline airline2 = new Airline();
        airline2.setId("A1");
        airline2.setAirlineName("IndiGo");
        airline2.setAirlineCode("6E");
        airline2.setCountry("India");

        assertEquals(airline, airline2);
        assertEquals(airline.hashCode(), airline2.hashCode());

        // negative equality
        airline2.setCountry("USA");
        assertNotEquals(airline, airline2);

        // insertion into HashSet
        HashSet<Airline> set = new HashSet<>();
        set.add(airline);
        assertTrue(set.contains(airline));
    }

    @Test
    void testAirlineEdgeCases() {
        Airline airline = new Airline();

        airline.setId(null);
        airline.setAirlineName("");
        airline.setAirlineCode(null);
        airline.setCountry(" ");

        assertNull(airline.getId());
        assertEquals("", airline.getAirlineName());
        assertNull(airline.getAirlineCode());
        assertEquals(" ", airline.getCountry());

        assertNotNull(airline.toString());
    }
}
