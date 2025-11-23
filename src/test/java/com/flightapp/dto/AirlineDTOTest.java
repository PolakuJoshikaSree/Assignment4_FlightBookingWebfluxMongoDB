package com.flightapp.dto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AirlineDTOTest {

    @Test
    void testAirlineDTO() {
        AirlineDTO dto = new AirlineDTO();

        // set values
        dto.setId(1L);
        dto.setAirlineName("Air India");
        dto.setAirlineCode("AI");
        dto.setCountry("India");

        // field assertions
        assertEquals(1L, dto.getId());
        assertEquals("Air India", dto.getAirlineName());
        assertEquals("AI", dto.getAirlineCode());
        assertEquals("India", dto.getCountry());

        // basic sanity checks – no primitive vs null, no same expr
        assertNotNull(dto.getAirlineName());
        assertNotNull(dto.getAirlineCode());
        assertNotNull(dto.getCountry());

        // just call hashCode / toString for coverage, no assertions needed
        dto.hashCode();
        dto.toString();
    }
}
