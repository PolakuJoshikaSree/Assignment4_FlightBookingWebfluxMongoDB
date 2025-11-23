package com.flightapp.service;

import com.flightapp.model.Payment;
import com.flightapp.model.enums.PaymentStatus;
import com.flightapp.repository.PaymentRepository;
import com.flightapp.request.PaymentRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class PaymentService {

    // Used to fetch booking details before creating a payment.
    private final BookingService bookingService;

    // Repository to store payment records.
    private final PaymentRepository repo;

    // Creates a payment entry for a specific PNR.
    public Mono<Payment> pay(String pnr, PaymentRequest req) {
        return bookingService.getBooking(pnr)
                .flatMap(booking -> {
                    Payment p = new Payment();
                    p.setAmount(req.getAmount());
                    p.setPaymentMode(req.getPaymentMode());
                    p.setPaymentTime(LocalDateTime.now());
                    p.setStatus(PaymentStatus.SUCCESS.name());
                    p.setBookingId(booking.getId());
                    return repo.save(p);
                });
    }

    // Fetches payment details using its ID.
    public Mono<Payment> getPayment(String id) {
        return repo.findById(id);
    }

    // Deletes payment from the system.
    public Mono<Void> deletePayment(String id) {
        return repo.deleteById(id);
    }
}
