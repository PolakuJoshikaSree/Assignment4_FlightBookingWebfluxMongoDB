package com.flightapp.dto;

import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import java.time.LocalTime;
import static org.junit.jupiter.api.Assertions.*;

class FlightDTOTest {

    @Test
    void testDTO() {
        FlightDTO dto = new FlightDTO();

        dto.setId(1L);
        dto.setFlightNumber("AI101");
        dto.setFromPlace("Delhi");
        dto.setToPlace("Mumbai");
        dto.setFlightDate(LocalDate.now());
        dto.setDepartureTime(LocalTime.NOON);
        dto.setArrivalTime(LocalTime.MIDNIGHT);
        dto.setTotalSeats(180);
        dto.setBookedSeats(20);
        dto.setPrice(5000);
        dto.setMealTypeAvailable("Veg");
        dto.setAirlineId(2L);

        assertEquals("AI101", dto.getFlightNumber());
        assertEquals("Delhi", dto.getFromPlace());
        assertEquals(180, dto.getTotalSeats());
    }
}
