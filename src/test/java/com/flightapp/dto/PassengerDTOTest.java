package com.flightapp.dto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PassengerDTOTest {

    @Test
    void testNoArgsConstructorAndSettersGetters() {
        PassengerDTO dto = new PassengerDTO();
        dto.setId(1L);
        dto.setPassengerName("John Doe");
        dto.setAge(30);
        dto.setGender("Male");
        dto.setMealPreference("VEG");
        dto.setLuggageWeight(15.5);

        assertEquals(1L, dto.getId());
        assertEquals("John Doe", dto.getPassengerName());
        assertEquals(30, dto.getAge());
        assertEquals("Male", dto.getGender());
        assertEquals("VEG", dto.getMealPreference());
        assertEquals(15.5, dto.getLuggageWeight());
    }

    @Test
    void testEqualsHashCodeAndToString() {
        PassengerDTO dto1 = new PassengerDTO();
        dto1.setId(1L);
        dto1.setPassengerName("John Doe");
        dto1.setAge(30);
        dto1.setGender("Male");
        dto1.setMealPreference("VEG");
        dto1.setLuggageWeight(15.5);

        PassengerDTO dto2 = new PassengerDTO();
        dto2.setId(1L);
        dto2.setPassengerName("John Doe");
        dto2.setAge(30);
        dto2.setGender("Male");
        dto2.setMealPreference("VEG");
        dto2.setLuggageWeight(15.5);

        PassengerDTO dto3 = new PassengerDTO();
        dto3.setId(2L);

        assertEquals(dto1, dto2);
        assertEquals(dto1.hashCode(), dto2.hashCode());
        assertNotEquals(dto1, dto3);
        assertNotEquals(dto1, null);
        assertNotEquals(dto1, new Object());
        assertNotNull(dto1.toString());
    }
}
