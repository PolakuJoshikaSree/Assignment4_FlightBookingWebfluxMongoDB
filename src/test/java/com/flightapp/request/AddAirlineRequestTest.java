package com.flightapp.request;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class AddAirlineRequestTest {

    @Test
    void testAddAirlineRequest() {
        AddAirlineRequest req = new AddAirlineRequest();

        req.setAirlineName("Indigo");
        req.setAirlineCode("6E");
        req.setCountry("India");

        assertEquals("Indigo", req.getAirlineName());
        assertEquals("6E", req.getAirlineCode());
        assertEquals("India", req.getCountry());
    }
}
