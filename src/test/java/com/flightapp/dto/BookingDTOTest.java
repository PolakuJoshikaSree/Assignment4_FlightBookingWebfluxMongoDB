package com.flightapp.dto;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BookingDTOTest {

    @Test
    void testBookingDTO() {
        BookingDTO dto = new BookingDTO();

        LocalDateTime now = LocalDateTime.now();

        PassengerDTO passenger = new PassengerDTO();
        passenger.setId(1L);
        passenger.setPassengerName("John");
        passenger.setAge(25);
        passenger.setGender("MALE");
        passenger.setMealPreference("VEG");
        passenger.setLuggageWeight(15.0);

        dto.setId(1L);
        dto.setPnr("PNR123");
        dto.setEmail("test@mail.com");
        dto.setPrimaryPassenger("John");
        dto.setSeatsBooked(2);
        dto.setBookingTime(now);
        dto.setStatus("CONFIRMED");
        dto.setFlightId(10L);
        dto.setPassengers(List.of(passenger));

        // field assertions
        assertEquals(1L, dto.getId());
        assertEquals("PNR123", dto.getPnr());
        assertEquals("test@mail.com", dto.getEmail());
        assertEquals("John", dto.getPrimaryPassenger());
        assertEquals(2, dto.getSeatsBooked());
        assertEquals(now, dto.getBookingTime());
        assertEquals("CONFIRMED", dto.getStatus());
        assertEquals(10L, dto.getFlightId());
        assertNotNull(dto.getPassengers());
        assertEquals(1, dto.getPassengers().size());
        assertEquals("John", dto.getPassengers().get(0).getPassengerName());

        // sanity calls for coverage
        dto.hashCode();
        dto.toString();
    }
}
