package com.flightapp.dto;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class AirlineDTOTest {

    @Test
    void testAirlineDTO() {
        AirlineDTO dto = new AirlineDTO();

        dto.setId(1L);
        dto.setAirlineName("Air India");
        dto.setAirlineCode("AI");
        dto.setCountry("India");

        // FIELD ASSERTIONS
        assertEquals(1L, dto.getId());
        assertEquals("Air India", dto.getAirlineName());
        assertEquals("AI", dto.getAirlineCode());
        assertEquals("India", dto.getCountry());

        // SAFE CHECKS
        assertNotNull(dto.toString());

        // hashCode sanity
        int hash = dto.hashCode();
        assertTrue(hash != 0);  
    }
}
