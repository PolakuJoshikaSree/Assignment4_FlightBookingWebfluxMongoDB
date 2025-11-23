package com.flightapp.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class PassengerTest {

    @Test
    void testPassengerModel() {
        Passenger p = new Passenger();

        p.setId("P1");
        p.setPassengerName("John");
        p.setGender("MALE");
        p.setAge(25);
        p.setMealPreference("VEG");
        p.setLuggageWeight(12.5);
        p.setBookingId("B1");

        assertEquals("P1", p.getId());
        assertEquals("John", p.getPassengerName());
        assertEquals("MALE", p.getGender());
        assertEquals(25, p.getAge());
        assertEquals("VEG", p.getMealPreference());
        assertEquals(12.5, p.getLuggageWeight());
        assertEquals("B1", p.getBookingId());
        assertNotNull(p.toString());
    }

    @Test
    void testPassengerEquals() {
        Passenger p1 = new Passenger();
        p1.setId("P1");

        Passenger p2 = new Passenger();
        p2.setId("P1");

        Passenger p3 = new Passenger();
        p3.setId("X");

        assertEquals(p1, p2);
        assertNotEquals(p1, p3);
        assertNotEquals(p1, null);
    }
}
