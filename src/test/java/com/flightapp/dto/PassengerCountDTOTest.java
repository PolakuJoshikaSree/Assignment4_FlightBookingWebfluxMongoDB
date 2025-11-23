package com.flightapp.dto;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class PassengerCountDTOTest {

    @Test
    void testDTO() {
        PassengerCountDTO dto = new PassengerCountDTO();
        dto.setFlightId("F100");
        dto.setTotalPassengers(120);

        assertEquals("F100", dto.getFlightId());
        assertEquals(120, dto.getTotalPassengers());
    }
}
