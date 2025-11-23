package com.flightapp.dto;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class PassengerDTOTest {

    @Test
    void testDTO() {
        PassengerDTO dto = new PassengerDTO();

        dto.setId(1L);
        dto.setPassengerName("John");
        dto.setAge(22);
        dto.setGender("MALE");
        dto.setMealPreference("VEG");
        dto.setLuggageWeight(10);

        assertEquals(1L, dto.getId());
        assertEquals("John", dto.getPassengerName());
        assertEquals(22, dto.getAge());
        assertEquals("VEG", dto.getMealPreference());
    }
}
