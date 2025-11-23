package com.flightapp.dto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FlightSeatStatsDTOTest {

    @Test
    void testFlightSeatStatsDTO() {
        FlightSeatStatsDTO dto = new FlightSeatStatsDTO(
                "AI101", "Delhi", "Mumbai", "2025-01-01", 180, 50, 130
        );

        assertEquals("AI101", dto.getFlightNumber());
        assertEquals("Delhi", dto.getFromPlace());
        assertEquals("Mumbai", dto.getToPlace());
        assertEquals("2025-01-01", dto.getFlightDate());
        assertEquals(180, dto.getTotalSeats());
        assertEquals(50, dto.getTotalSeatsBooked());
        assertEquals(130, dto.getRemainingSeats());

        assertNotEquals(dto, new FlightSeatStatsDTO());
        assertNotNull(dto.toString());
    }
}
