package com.flightapp.dto;

import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

class FlightDTOTest {

    @Test
    void testFlightDTO() {
        FlightDTO dto = new FlightDTO();

        LocalDate d = LocalDate.now();
        LocalTime t = LocalTime.of(10, 30);

        dto.setId(1L);
        dto.setFlightNumber("AI101");
        dto.setFromPlace("Delhi");
        dto.setToPlace("Mumbai");
        dto.setFlightDate(d);
        dto.setDepartureTime(t);
        dto.setArrivalTime(t);
        dto.setBaggageLimitKg(15);
        dto.setTotalSeats(180);
        dto.setBookedSeats(85);
        dto.setPrice(4500);
        dto.setMealTypeAvailable("Veg");
        dto.setAirlineId(99L);

        assertEquals(1L, dto.getId());
        assertEquals("AI101", dto.getFlightNumber());
        assertEquals("Delhi", dto.getFromPlace());
        assertEquals("Mumbai", dto.getToPlace());
        assertEquals(d, dto.getFlightDate());
        assertEquals(t, dto.getDepartureTime());
        assertEquals(15, dto.getBaggageLimitKg());
        assertEquals(180, dto.getTotalSeats());
        assertEquals(85, dto.getBookedSeats());
        assertEquals(4500, dto.getPrice());
        assertEquals("Veg", dto.getMealTypeAvailable());
        assertEquals(99L, dto.getAirlineId());

        assertNotNull(dto.toString());
    }
}
