package com.flightapp.service;

import com.flightapp.model.Booking;
import com.flightapp.model.Payment;
import com.flightapp.repository.PaymentRepository;
import com.flightapp.request.PaymentRequest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Mono;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PaymentServiceTest {

    @Mock
    private BookingService bookingService;

    @Mock
    private PaymentRepository paymentRepo;

    @InjectMocks
    private PaymentService service;

    @Test
    void testPay() {
        // Booking must exist before payment is processed.
        Booking booking = new Booking();
        booking.setId("B1");

        when(bookingService.getBooking("PNR123"))
                .thenReturn(Mono.just(booking));

        // Mocking what MongoDB would return after saving.
        when(paymentRepo.save(any(Payment.class)))
                .thenReturn(Mono.just(new Payment()));

        // Creating a simple payment request.
        PaymentRequest req = new PaymentRequest();
        req.setAmount(5000);
        req.setPaymentMode("UPI");

        // Calling the method to test.
        Payment result = service.pay("PNR123", req).block();

        // Should not be null if save worked.
        assertNotNull(result);

        verify(paymentRepo, times(1)).save(any(Payment.class));
    }
}
