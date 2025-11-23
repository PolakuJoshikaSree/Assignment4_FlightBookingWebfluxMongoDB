package com.flightapp.dto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FlightCountPerAirlineDTOTest {

    @Test
    void testFlightCountDTO() {
        FlightCountPerAirlineDTO dto = new FlightCountPerAirlineDTO(
                "AI", "Air India", 10
        );

        assertEquals("AI", dto.getAirlineCode());
        assertEquals("Air India", dto.getAirlineName());
        assertEquals(10, dto.getTotalFlights());

        assertNotEquals(dto, new FlightCountPerAirlineDTO());
        assertNotNull(dto.toString());
    }
}
