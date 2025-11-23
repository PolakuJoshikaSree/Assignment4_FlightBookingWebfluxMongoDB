package com.flightapp.dto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PassengerDTOTest {

    @Test
    void testPassengerDTO() {
        PassengerDTO dto = new PassengerDTO();

        dto.setId(1L);
        dto.setPassengerName("John");
        dto.setAge(30);
        dto.setGender("MALE");
        dto.setMealPreference("VEG");
        dto.setLuggageWeight(15.0);

        assertEquals(1L, dto.getId());
        assertEquals("John", dto.getPassengerName());
        assertEquals(30, dto.getAge());
        assertEquals("MALE", dto.getGender());
        assertEquals("VEG", dto.getMealPreference());
        assertEquals(15.0, dto.getLuggageWeight());

        assertNotEquals(dto, new PassengerDTO());
        assertNotNull(dto.toString());
    }
}
