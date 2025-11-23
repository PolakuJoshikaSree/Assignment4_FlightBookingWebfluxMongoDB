package com.flightapp.dto;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class SeatsPerFlightDTOTest {

    @Test
    void testDTOFields() {
        SeatsPerFlightDTO dto = new SeatsPerFlightDTO();
        dto.setFlightId("F1");
        dto.setTotalSeatsBooked(50);

        assertEquals("F1", dto.getFlightId());
        assertEquals(50, dto.getTotalSeatsBooked());
    }
}
