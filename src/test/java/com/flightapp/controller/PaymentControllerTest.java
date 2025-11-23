package com.flightapp.controller;

import com.flightapp.model.Payment;
import com.flightapp.request.PaymentRequest;
import com.flightapp.service.PaymentService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

@WebFluxTest(PaymentController.class)
class PaymentControllerTest {

    @Autowired
    private WebTestClient client;

    @MockBean
    private PaymentService service;

    @Test
    void testPay() {
        PaymentRequest req = new PaymentRequest();
        req.setAmount(500);

        Payment p = new Payment();
        p.setId("P1");

        Mockito.when(service.pay(Mockito.eq("PNR1"), Mockito.any()))
                .thenReturn(Mono.just(p));

        client.post().uri("/api/payments/pay/PNR1")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(req)
                .exchange()
                .expectStatus().isCreated();
    }

    @Test
    void testGet() {
        Payment p = new Payment();
        p.setId("P1");

        Mockito.when(service.getPayment("P1")).thenReturn(Mono.just(p));

        client.get().uri("/api/payments/P1")
                .exchange()
                .expectStatus().isOk();
    }

    @Test
    void testDelete() {
        Mockito.when(service.deletePayment("P1")).thenReturn(Mono.empty());

        client.delete().uri("/api/payments/P1")
                .exchange()
                .expectStatus().isNoContent();
    }
}
