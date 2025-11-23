package com.flightapp.dto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PassengerCountDTOTest {

    @Test
    void testAllArgsConstructorAndGetters() {
        PassengerCountDTO dto = new PassengerCountDTO(
                "FLIGHT123",
                120
        );

        assertEquals("FLIGHT123", dto.getFlightId());
        assertEquals(120, dto.getTotalPassengers());
    }

    @Test
    void testNoArgsConstructorSettersEqualsHashCodeToString() {
        PassengerCountDTO dto1 = new PassengerCountDTO();
        dto1.setFlightId("FLIGHT123");
        dto1.setTotalPassengers(120);

        PassengerCountDTO dto2 = new PassengerCountDTO();
        dto2.setFlightId("FLIGHT123");
        dto2.setTotalPassengers(120);

        PassengerCountDTO dto3 = new PassengerCountDTO();
        dto3.setFlightId("OTHER");

        assertEquals(dto1, dto2);
        assertEquals(dto1.hashCode(), dto2.hashCode());
        assertNotEquals(dto1, dto3);
        assertNotEquals(dto1, null);
        assertNotEquals(dto1, new Object());
        assertNotNull(dto1.toString());
    }
}
