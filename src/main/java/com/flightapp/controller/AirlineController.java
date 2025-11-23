package com.flightapp.controller;

import com.flightapp.model.Airline;
import com.flightapp.request.AddAirlineRequest;
import com.flightapp.service.AirlineService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/airlines")
public class AirlineController {

    // AirlineService is injected here. This controller only delegates work to the service layer.
    private final AirlineService service;

    // Adds a new airline record into MongoDB. Expects basic airline details from the request body.
    @PostMapping("/add")
    public Mono<ResponseEntity<Airline>> add(@RequestBody AddAirlineRequest req) {
        return service.addAirline(req)
                .map(saved -> ResponseEntity.status(201).body(saved));
    }

    // Returns all airlines stored in the database. Useful for admin listing.
    @GetMapping("/all")
    public Flux<Airline> all() {
        return service.getAll();
    }

    // Fetches airline details using its code (like "AI" or "UK"). Returns 404 if not found.
    @GetMapping("/{code}")
    public Mono<ResponseEntity<Airline>> getByCode(@PathVariable String code) {
        return service.getAirlineByCode(code)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    // Deletes an airline by its database ID. Used for admin cleanup operations.
    @DeleteMapping("/{id}")
    public Mono<ResponseEntity<Void>> delete(@PathVariable String id) {
        return service.deleteAirlineById(id)
                .then(Mono.just(ResponseEntity.noContent().build()));
    }
}
