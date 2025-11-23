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
        f.setFlightDate(LocalDate.now());
        f.setDepartureTime(LocalTime.now());
        f.setArrivalTime(LocalTime.now());
        f.setTotalSeats(180);
        f.setBookedSeats(10);
        f.setPrice(4500);
        f.setMealTypeAvailable("Veg");
        f.setBaggageLimitKg(15);

        assertEquals("F1", f.getId());
        assertEquals("AI101", f.getFlightNumber());
        assertEquals("AI", f.getAirlineCode());
        assertEquals("Delhi", f.getFromPlace());
        assertEquals(180, f.getTotalSeats());
    }
}
