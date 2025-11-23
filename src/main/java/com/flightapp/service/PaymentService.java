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

    private final BookingService bookingService;
    private final PaymentRepository repo;

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

    public Mono<Payment> getPayment(String id) {
        return repo.findById(id);
    }

    public Mono<Void> deletePayment(String id) {
        return repo.deleteById(id);
    }

}
