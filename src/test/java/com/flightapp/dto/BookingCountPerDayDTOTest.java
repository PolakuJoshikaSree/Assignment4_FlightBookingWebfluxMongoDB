package com.flightapp.dto;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class BookingCountPerDayDTOTest {

    @Test
    void testDTO() {
        BookingCountPerDayDTO dto = new BookingCountPerDayDTO();
        dto.setDate("2025-11-20");
        dto.setTotalBookings(20);

        assertEquals("2025-11-20", dto.getDate());
        assertEquals(20, dto.getTotalBookings());
    }
}
