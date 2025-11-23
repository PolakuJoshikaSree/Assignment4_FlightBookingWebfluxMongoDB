package com.flightapp.dto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FlightSeatStatsDTOTest {

    @Test
    void testAllArgsConstructorAndGetters() {
        FlightSeatStatsDTO dto = new FlightSeatStatsDTO(
                "FN123",
                "HYD",
                "BLR",
                "2025-12-01",
                180,
                120,
                60
        );

        assertEquals("FN123", dto.getFlightNumber());
        assertEquals("HYD", dto.getFromPlace());
        assertEquals("BLR", dto.getToPlace());
        assertEquals("2025-12-01", dto.getFlightDate());
        assertEquals(180, dto.getTotalSeats());
        assertEquals(120, dto.getTotalSeatsBooked());
        assertEquals(60, dto.getRemainingSeats());
    }

    @Test
    void testNoArgsConstructorSettersEqualsHashCodeToString() {
        FlightSeatStatsDTO dto1 = new FlightSeatStatsDTO();
        dto1.setFlightNumber("FN123");
        dto1.setFromPlace("HYD");
        dto1.setToPlace("BLR");
        dto1.setFlightDate("2025-12-01");
        dto1.setTotalSeats(180);
        dto1.setTotalSeatsBooked(120);
        dto1.setRemainingSeats(60);

        FlightSeatStatsDTO dto2 = new FlightSeatStatsDTO();
        dto2.setFlightNumber("FN123");
        dto2.setFromPlace("HYD");
        dto2.setToPlace("BLR");
        dto2.setFlightDate("2025-12-01");
        dto2.setTotalSeats(180);
        dto2.setTotalSeatsBooked(120);
        dto2.setRemainingSeats(60);

        FlightSeatStatsDTO dto3 = new FlightSeatStatsDTO();
        dto3.setFlightNumber("DIFFERENT");

        assertEquals(dto1, dto2);
        assertEquals(dto1.hashCode(), dto2.hashCode());
        assertNotEquals(dto1, dto3);
        assertNotEquals(dto1, null);
        assertNotEquals(dto1, new Object());
        assertNotNull(dto1.toString());
    }
}
