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
        p.setLuggageWeight(12);
        p.setBookingId("B1");

        assertEquals("P1", p.getId());
        assertEquals("John", p.getPassengerName());
        assertEquals("MALE", p.getGender());
        assertEquals(25, p.getAge());
        assertEquals(12, p.getLuggageWeight());
    }
}
