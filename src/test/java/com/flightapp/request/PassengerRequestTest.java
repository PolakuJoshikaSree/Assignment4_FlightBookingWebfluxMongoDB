package com.flightapp.request;

import com.flightapp.model.enums.Gender;
import com.flightapp.model.enums.MealType;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PassengerRequestTest {

    @Test
    void testPassengerRequest() {
        PassengerRequest p = new PassengerRequest();

        p.setName("Alice");
        p.setAge(25);
        p.setGender(Gender.FEMALE);
        p.setMealPreference(MealType.VEG);
        p.setLuggageWeight(12.5);

        assertEquals("Alice", p.getName());
        assertEquals(25, p.getAge());
        assertEquals(Gender.FEMALE, p.getGender());
        assertEquals(MealType.VEG, p.getMealPreference());
        assertEquals(12.5, p.getLuggageWeight());
    }
}
