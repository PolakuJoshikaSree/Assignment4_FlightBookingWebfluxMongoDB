package com.flightapp.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AirlineTest {

    @Test
    void testAirlineModel() {
        Airline a = new Airline();
        a.setId("A1");
        a.setAirlineName("Air India");
        a.setAirlineCode("AI");
        a.setCountry("India");

        assertEquals("A1", a.getId());
        assertEquals("Air India", a.getAirlineName());
        assertEquals("AI", a.getAirlineCode());
        assertEquals("India", a.getCountry());

        assertNotEquals(a, new Airline()); // avoids same-instance comparison
        assertNotNull(a.toString());
    }
}
