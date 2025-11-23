package com.flightapp.dto;

import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class PaymentDTOTest {

    @Test
    void testPaymentDTO() {
        PaymentDTO dto = new PaymentDTO();

        LocalDateTime now = LocalDateTime.now();

        dto.setId(10L);
        dto.setAmount(5000);
        dto.setPaymentMode("UPI");
        dto.setStatus("SUCCESS");
        dto.setPaymentTime(now);
        dto.setBookingId(200L);

        assertEquals(10L, dto.getId());
        assertEquals(5000, dto.getAmount());
        assertEquals("UPI", dto.getPaymentMode());
        assertEquals("SUCCESS", dto.getStatus());
        assertEquals(now, dto.getPaymentTime());
        assertEquals(200L, dto.getBookingId());

        assertNotNull(dto.toString());
    }
}
