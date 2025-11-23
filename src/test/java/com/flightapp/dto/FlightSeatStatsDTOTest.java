package com.flightapp.dto;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class FlightSeatStatsDTOTest {

    @Test
    void testDTO() {
        FlightSeatStatsDTO dto = new FlightSeatStatsDTO(
                "AI101", "Delhi", "Mumbai", "2025-11-22",
                180, 150, 30
        );

        assertEquals("AI101", dto.getFlightNumber());
        assertEquals("Delhi", dto.getFromPlace());
        assertEquals(180, dto.getTotalSeats());
        assertEquals(30, dto.getRemainingSeats());
    }
}
