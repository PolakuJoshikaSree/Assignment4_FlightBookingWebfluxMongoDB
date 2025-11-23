package com.flightapp.dto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BookingCountPerDayDTOTest {

    @Test
    void testAllArgsConstructorAndGetters() {
        BookingCountPerDayDTO dto = new BookingCountPerDayDTO(
                "2025-12-01",
                50
        );

        assertEquals("2025-12-01", dto.getDate());
        assertEquals(50, dto.getTotalBookings());
    }

    @Test
    void testNoArgsConstructorSettersEqualsHashCodeToString() {
        BookingCountPerDayDTO dto1 = new BookingCountPerDayDTO();
        dto1.setDate("2025-12-01");
        dto1.setTotalBookings(50);

        BookingCountPerDayDTO dto2 = new BookingCountPerDayDTO();
        dto2.setDate("2025-12-01");
        dto2.setTotalBookings(50);

        BookingCountPerDayDTO dto3 = new BookingCountPerDayDTO();
        dto3.setDate("2025-12-02");

        assertEquals(dto1, dto2);
        assertEquals(dto1.hashCode(), dto2.hashCode());
        assertNotEquals(dto1, dto3);
        assertNotEquals(dto1, null);
        assertNotEquals(dto1, new Object());
        assertNotNull(dto1.toString());
    }
}
