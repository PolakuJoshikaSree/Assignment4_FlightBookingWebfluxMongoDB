package com.flightapp.request;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class PaymentRequestTest {

    @Test
    void testPaymentRequest() {
        PaymentRequest req = new PaymentRequest();

        req.setPaymentMode("UPI");
        req.setAmount(4999.99);

        assertEquals("UPI", req.getPaymentMode());
        assertEquals(4999.99, req.getAmount());
    }
}
