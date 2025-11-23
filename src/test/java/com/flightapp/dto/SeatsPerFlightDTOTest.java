package com.flightapp.dto;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class SeatsPerFlightDTOTest {

    @Test
    void testSeatsPerFlightDTO() {
        SeatsPerFlightDTO dto = new SeatsPerFlightDTO();

        dto.setFlightId("FL100");
        dto.setTotalSeatsBooked(40);

        assertEquals("FL100", dto.getFlightId());
        assertEquals(40, dto.getTotalSeatsBooked());
    }
}
