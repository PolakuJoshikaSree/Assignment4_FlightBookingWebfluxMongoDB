package com.flightapp.model;

import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class PaymentTest {

    @Test
    void testPaymentModel() {
        Payment p = new Payment();
        LocalDateTime now = LocalDateTime.now();

        p.setId("PAY1");
        p.setAmount(500.0);
        p.setPaymentMode("UPI");
        p.setPaymentTime(now);
        p.setStatus("SUCCESS");
        p.setBookingId("B1");

        assertEquals("PAY1", p.getId());
        assertEquals(500.0, p.getAmount());
        assertEquals("UPI", p.getPaymentMode());
        assertEquals(now, p.getPaymentTime());
        assertEquals("SUCCESS", p.getStatus());
        assertEquals("B1", p.getBookingId());

        assertNotEquals(p, new Payment());
        assertNotNull(p.toString());
    }
}
