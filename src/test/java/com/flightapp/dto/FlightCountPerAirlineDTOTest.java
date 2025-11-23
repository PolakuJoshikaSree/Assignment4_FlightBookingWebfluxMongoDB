package com.flightapp.dto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FlightCountPerAirlineDTOTest {

    @Test
    void testAllArgsConstructorAndGetters() {
        FlightCountPerAirlineDTO dto = new FlightCountPerAirlineDTO(
                "6E",
                "IndiGo",
                25
        );

        assertEquals("6E", dto.getAirlineCode());
        assertEquals("IndiGo", dto.getAirlineName());
        assertEquals(25, dto.getTotalFlights());
    }

    @Test
    void testNoArgsConstructorSettersEqualsHashCodeToString() {
        FlightCountPerAirlineDTO dto1 = new FlightCountPerAirlineDTO();
        dto1.setAirlineCode("6E");
        dto1.setAirlineName("IndiGo");
        dto1.setTotalFlights(25);

        FlightCountPerAirlineDTO dto2 = new FlightCountPerAirlineDTO();
        dto2.setAirlineCode("6E");
        dto2.setAirlineName("IndiGo");
        dto2.setTotalFlights(25);

        FlightCountPerAirlineDTO dto3 = new FlightCountPerAirlineDTO();
        dto3.setAirlineCode("AI");

        assertEquals(dto1, dto2);
        assertEquals(dto1.hashCode(), dto2.hashCode());
        assertNotEquals(dto1, dto3);
        assertNotEquals(dto1, null);
        assertNotEquals(dto1, new Object());
        assertNotNull(dto1.toString());
    }
}
