package com.flightapp.request;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class AddFlightRequestTest {

    @Test
    void testAddFlightRequest() {
        AddFlightRequest req = new AddFlightRequest();

        req.setFlightNumber("AI101");
        req.setFromPlace("Delhi");
        req.setToPlace("Mumbai");
        req.setFlightDate("2025-05-10");
        req.setDepartureTime("10:00");
        req.setArrivalTime("12:00");
        req.setTotalSeats(180);
        req.setPrice(5000.0);
        req.setBaggageLimitKg(15);
        req.setAirlineCode("AI");

        assertEquals("AI101", req.getFlightNumber());
        assertEquals("Delhi", req.getFromPlace());
        assertEquals("Mumbai", req.getToPlace());
        assertEquals("2025-05-10", req.getFlightDate());
        assertEquals("10:00", req.getDepartureTime());
        assertEquals("12:00", req.getArrivalTime());
        assertEquals(180, req.getTotalSeats());
        assertEquals(5000.0, req.getPrice());
        assertEquals(15, req.getBaggageLimitKg());
        assertEquals("AI", req.getAirlineCode());
    }
}
