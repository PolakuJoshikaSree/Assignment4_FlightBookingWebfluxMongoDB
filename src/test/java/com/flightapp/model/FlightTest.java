package com.flightapp.model;

import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

class FlightTest {

    @Test
    void testFlightModel() {
        Flight f = new Flight();

        f.setId("F1");
        f.setFlightNumber("AI101");
        f.setAirlineCode("AI");
        f.setFromPlace("Delhi");
        f.setToPlace("Mumbai");
        f.setFlightDate(LocalDate.parse("2025-01-01"));
        f.setDepartureTime(LocalTime.parse("10:00"));
        f.setArrivalTime(LocalTime.parse("12:00"));
        f.setTotalSeats(180);
        f.setBookedSeats(50);
        f.setPrice(4500.0);
        f.setMealTypeAvailable("Veg");
        f.setBaggageLimitKg(15);

        assertEquals("F1", f.getId());
        assertEquals("AI101", f.getFlightNumber());
        assertEquals("AI", f.getAirlineCode());
        assertEquals("Delhi", f.getFromPlace());
        assertEquals("Mumbai", f.getToPlace());
        assertEquals(180, f.getTotalSeats());

        assertNotEquals(f, new Flight());
        assertNotNull(f.toString());
    }
}
