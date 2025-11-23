package com.flightapp.model;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

class FlightTest {

    @Test
    void testFlightLifecycle() {
        Flight f = new Flight();

        LocalDate date = LocalDate.of(2025, 12, 1);
        LocalTime dep = LocalTime.of(9, 30);
        LocalTime arr = LocalTime.of(12, 15);

        f.setId("F1");
        f.setFlightNumber("AI202");
        f.setAirlineCode("AI");
        f.setFromPlace("Delhi");
        f.setToPlace("Mumbai");
        f.setFlightDate(date);
        f.setDepartureTime(dep);
        f.setArrivalTime(arr);
        f.setTotalSeats(180);
        f.setBookedSeats(90);
        f.setPrice(6500.75);
        f.setMealTypeAvailable("Veg/Non-Veg");
        f.setBaggageLimitKg(25.0);

        assertEquals("F1", f.getId());
        assertEquals("AI202", f.getFlightNumber());
        assertEquals("AI", f.getAirlineCode());
        assertEquals("Delhi", f.getFromPlace());
        assertEquals("Mumbai", f.getToPlace());
        assertEquals(date, f.getFlightDate());
        assertEquals(dep, f.getDepartureTime());
        assertEquals(arr, f.getArrivalTime());
        assertEquals(180, f.getTotalSeats());
        assertEquals(90, f.getBookedSeats());
        assertEquals(6500.75, f.getPrice());
        assertEquals("Veg/Non-Veg", f.getMealTypeAvailable());
        assertEquals(25.0, f.getBaggageLimitKg());

        assertNotNull(f.toString());
        assertTrue(f.toString().contains("AI202"));
    }

    @Test
    void testNulls() {
        Flight f = new Flight();

        f.setFlightNumber(null);
        f.setMealTypeAvailable(null);

        assertNull(f.getFlightNumber());
        assertNull(f.getMealTypeAvailable());

        assertNotNull(f.toString()); // toString never null
    }
}
