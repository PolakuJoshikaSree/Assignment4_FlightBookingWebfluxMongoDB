package com.flightapp.dto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FlightRevenueDTOTest {

    @Test
    void testAllArgsConstructorAndGetters() {
        FlightRevenueDTO dto = new FlightRevenueDTO(
                "FLIGHT123",
                12345.67
        );

        assertEquals("FLIGHT123", dto.getFlightId());
        assertEquals(12345.67, dto.getTotalRevenue());
    }

    @Test
    void testNoArgsConstructorSettersEqualsHashCodeToString() {
        FlightRevenueDTO dto1 = new FlightRevenueDTO();
        dto1.setFlightId("FLIGHT123");
        dto1.setTotalRevenue(12345.67);

        FlightRevenueDTO dto2 = new FlightRevenueDTO();
        dto2.setFlightId("FLIGHT123");
        dto2.setTotalRevenue(12345.67);

        FlightRevenueDTO dto3 = new FlightRevenueDTO();
        dto3.setFlightId("OTHER");

        assertEquals(dto1, dto2);
        assertEquals(dto1.hashCode(), dto2.hashCode());
        assertNotEquals(dto1, dto3);
        assertNotEquals(dto1, null);
        assertNotEquals(dto1, new Object());
        assertNotNull(dto1.toString());
    }
}
