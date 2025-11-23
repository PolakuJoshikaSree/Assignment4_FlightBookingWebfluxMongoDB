package com.flightapp.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PaymentTest {

    @Test
    void testPaymentLifecycle() {
        Payment p = new Payment();

        p.setId("P1");
        p.setAmount(5000.5);
        p.setStatus("SUCCESS");
        p.setBookingId("B1");

        assertEquals("P1", p.getId());
        assertEquals(5000.5, p.getAmount());
        assertEquals("SUCCESS", p.getStatus());
        assertEquals("B1", p.getBookingId());
        assertNotNull(p.toString());

        // Create identical object to test equals()
        Payment p2 = new Payment();
        p2.setId("P1");
        p2.setAmount(5000.5);
        p2.setStatus("SUCCESS");
        p2.setBookingId("B1");

        assertEquals(p, p2);
        assertEquals(p.hashCode(), p2.hashCode());
    }

    @Test
    void testNegativeAmount() {
        Payment p = new Payment();
        p.setAmount(-10);

        assertEquals(-10, p.getAmount());
        assertNotNull(p.toString());
    }
}
