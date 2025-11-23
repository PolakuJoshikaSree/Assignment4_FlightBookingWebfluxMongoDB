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
    private final AirlineService service;
    @PostMapping("/add")
    public Mono<ResponseEntity<Airline>> add(@RequestBody AddAirlineRequest req) {
        return service.addAirline(req)
                .map(saved -> ResponseEntity.status(201).body(saved));  
    }
    @GetMapping("/all")
    public Flux<Airline> all() {
        return service.getAll();
    }
    @GetMapping("/{code}")
    public Mono<ResponseEntity<Airline>> getByCode(@PathVariable String code) {
        return service.getAirlineByCode(code)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build()); 
    }
    @DeleteMapping("/{id}")
    public Mono<ResponseEntity<Void>> delete(@PathVariable String id) {
        return service.deleteAirlineById(id)
                .then(Mono.just(ResponseEntity.noContent().build())); 
    }
}
