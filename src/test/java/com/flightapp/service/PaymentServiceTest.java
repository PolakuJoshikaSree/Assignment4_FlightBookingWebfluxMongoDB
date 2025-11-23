package com.flightapp.service;

import com.flightapp.model.Booking;
import com.flightapp.model.Payment;
import com.flightapp.repository.PaymentRepository;
import com.flightapp.request.PaymentRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.mockito.Mockito.*;

class PaymentServiceTest {

    private BookingService bookingService;
    private PaymentRepository repo;
    private PaymentService service;

    @BeforeEach
    void setUp() {
        bookingService = mock(BookingService.class);
        repo = mock(PaymentRepository.class);
        service = new PaymentService(bookingService, repo);
    }

    @Test
    void testPay() {
        Booking b = new Booking(); b.setId("B1");
        Payment p = new Payment(); p.setAmount(500);

        when(bookingService.getBooking("PNR1")).thenReturn(Mono.just(b));
        when(repo.save(any())).thenReturn(Mono.just(p));

        PaymentRequest req = new PaymentRequest();
        req.setPaymentMode("UPI");
        req.setAmount(500);

        StepVerifier.create(service.pay("PNR1", req))
                .expectNext(p)
                .verifyComplete();
    }

    @Test
    void testPay_BookingNotFound() {
        when(bookingService.getBooking("X")).thenReturn(Mono.empty());

        StepVerifier.create(service.pay("X", new PaymentRequest()))
                .verifyComplete();
    }

    @Test
    void testGetPayment() {
        Payment p = new Payment(); p.setId("P1");

        when(repo.findById("P1")).thenReturn(Mono.just(p));

        StepVerifier.create(service.getPayment("P1"))
                .expectNext(p)
                .verifyComplete();
    }

    @Test
    void testDeletePayment() {
        when(repo.deleteById("P5")).thenReturn(Mono.empty());

        StepVerifier.create(service.deletePayment("P5"))
                .verifyComplete();
    }
}
