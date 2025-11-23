package com.flightapp.dto;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.time.LocalDateTime;
import java.util.Collections;

class BookingDTOTest {

    @Test
    void testDTO() {
        BookingDTO dto = new BookingDTO();

        dto.setId(100L);
        dto.setPnr("PNR123");
        dto.setEmail("test@gmail.com");
        dto.setPrimaryPassenger("John");
        dto.setSeatsBooked(3);
        dto.setBookingTime(LocalDateTime.now());
        dto.setStatus("CONFIRMED");
        dto.setFlightId(1L);
        dto.setPassengers(Collections.emptyList());

        assertEquals("PNR123", dto.getPnr());
        assertEquals(3, dto.getSeatsBooked());
        assertEquals("John", dto.getPrimaryPassenger());
    }
}
