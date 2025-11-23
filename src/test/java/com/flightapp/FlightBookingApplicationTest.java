package com.flightapp;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class FlightBookingApplicationTest {

    @Test
    void testMainMethod() {
        // Ensures the application class loads without starting the server
        assertDoesNotThrow(() -> 
            FlightBookingApplication.main(new String[] {
                    "--spring.main.web-application-type=none"
            })
        );
    }
}
