package com.flightapp.request;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class FlightSearchRequestTest {

    @Test
    void testFlightSearchRequest() {
        FlightSearchRequest req = new FlightSearchRequest();

        req.setFromPlace("Delhi");
        req.setToPlace("Chennai");
        req.setFlightDate("2025-06-10");

        assertEquals("Delhi", req.getFromPlace());
        assertEquals("Chennai", req.getToPlace());
        assertEquals("2025-06-10", req.getFlightDate());
    }
}
