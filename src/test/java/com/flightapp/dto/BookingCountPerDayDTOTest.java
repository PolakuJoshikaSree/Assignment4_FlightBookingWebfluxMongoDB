package com.flightapp.dto;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class BookingCountPerDayDTOTest {

    @Test
    void testBookingCountPerDayDTO() {
        BookingCountPerDayDTO d1 = new BookingCountPerDayDTO("2025-01-01", 50);
        BookingCountPerDayDTO d2 = new BookingCountPerDayDTO("2025-01-01", 50);

        assertEquals(d1, d2);
        assertEquals(d1.hashCode(), d2.hashCode());
        assertNotNull(d1.toString());
    }
}
