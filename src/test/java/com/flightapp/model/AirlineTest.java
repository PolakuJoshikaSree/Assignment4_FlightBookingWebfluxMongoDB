package com.flightapp.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class AirlineTest {

    @Test
    void testAirlineModel() {
        Airline a = new Airline();
        a.setId("1");
        a.setAirlineName("Indigo");
        a.setAirlineCode("6E");
        a.setCountry("India");

        assertEquals("1", a.getId());
        assertEquals("Indigo", a.getAirlineName());
        assertEquals("6E", a.getAirlineCode());
        assertEquals("India", a.getCountry());
    }
}
