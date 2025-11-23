package com.flightapp.dto;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class FlightRevenueDTOTest {

    @Test
    void testFlightRevenueDTO() {
        FlightRevenueDTO d1 = new FlightRevenueDTO("F1", 10000.0);
        FlightRevenueDTO d2 = new FlightRevenueDTO("F1", 10000.0);

        assertEquals(d1, d2);
        assertEquals(d1.hashCode(), d2.hashCode());
        assertNotNull(d1.toString());
    }
}
