package com.flightapp.controller;

import com.flightapp.model.Payment;
import com.flightapp.request.PaymentRequest;
import com.flightapp.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/payments")
public class PaymentController {
    private final PaymentService service;

    @PostMapping("/pay/{pnr}")
    public Mono<ResponseEntity<Payment>> pay(
            @PathVariable String pnr,
            @RequestBody PaymentRequest req) {
        return service.pay(pnr, req)
                .map(saved -> ResponseEntity.status(201).body(saved));
    }
    @GetMapping("/{id}")
    public Mono<ResponseEntity<Payment>> get(@PathVariable String id) {
        return service.getPayment(id)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build()); 
    }
    @DeleteMapping("/{id}")
    public Mono<ResponseEntity<Void>> delete(@PathVariable String id) {
        return service.deletePayment(id)
                .then(Mono.just(ResponseEntity.noContent().build())); 
    }
}
