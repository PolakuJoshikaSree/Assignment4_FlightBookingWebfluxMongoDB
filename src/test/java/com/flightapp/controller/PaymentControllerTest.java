package com.flightapp.controller;

import com.flightapp.model.Payment;
import com.flightapp.request.PaymentRequest;
import com.flightapp.service.PaymentService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import reactor.core.publisher.Mono;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PaymentControllerTest {

    @Mock
    private PaymentService service;

    @InjectMocks
    private PaymentController controller;

    @Test
    void testPay() {
        // Creating a payment request like real user input.
        PaymentRequest req = new PaymentRequest();
        req.setAmount(5000);

        // Mock saved payment.
        Payment p = new Payment();
        p.setAmount(5000);

        when(service.pay("PNR1", req)).thenReturn(Mono.just(p));

        ResponseEntity<Payment> response = controller.pay("PNR1", req).block();

        // Expecting 201 created + correct amount.
        assertEquals(201, response.getStatusCode().value());
        assertEquals(5000, response.getBody().getAmount());
    }

    @Test
    void testGetPayment_found() {
        Payment pay = new Payment();
        pay.setId("33");

        when(service.getPayment("33")).thenReturn(Mono.just(pay));

        ResponseEntity<Payment> response = controller.get("33").block();

        // If payment exists -> 200 OK.
        assertEquals(200, response.getStatusCode().value());
        assertEquals("33", response.getBody().getId());
    }

    @Test
    void testGetPayment_notFound() {
        when(service.getPayment("BAD")).thenReturn(Mono.empty());

        ResponseEntity<Payment> response = controller.get("BAD").block();

        // Not found case -> 404 expected.
        assertEquals(404, response.getStatusCode().value());
    }

    @Test
    void testDeletePayment() {
        when(service.deletePayment("10")).thenReturn(Mono.empty());

        ResponseEntity<Void> response = controller.delete("10").block();

        // Delete should return 204.
        assertEquals(204, response.getStatusCode().value());
    }
}
