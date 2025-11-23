package com.flightapp.dto;

import org.junit.jupiter.api.Test;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class FlightSearchDTOTest {

    @Test
    void testFlightSearchDTO() {
        FlightSearchDTO dto = new FlightSearchDTO();

        LocalDate d = LocalDate.now();

        dto.setFrom("Chennai");
        dto.setTo("Delhi");
        dto.setDate(d);

        assertEquals("Chennai", dto.getFrom());
        assertEquals("Delhi", dto.getTo());
        assertEquals(d, dto.getDate());

        assertNotNull(dto.toString());
    }
}
