package com.flightapp.controller;

import com.flightapp.dto.FlightSeatStatsDTO;
import com.flightapp.model.Flight;
import com.flightapp.request.AddFlightRequest;
import com.flightapp.request.FlightSearchRequest;
import com.flightapp.service.FlightService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/flights")
public class FlightController {
    private final FlightService service;
    @PostMapping("/add")
    public Mono<ResponseEntity<Flight>> addFlight(@RequestBody AddFlightRequest req) {
        return service.addFlight(req)
                .map(saved -> ResponseEntity.status(201).body(saved)); 
    }
    @PostMapping("/search")
    public Flux<Flight> searchFlights(@RequestBody FlightSearchRequest req) {
        return service.searchFlights(req);
    }
    @GetMapping("/{id}")
    public Mono<ResponseEntity<Flight>> getFlightById(@PathVariable String id) {
        return service.getFlightById(id)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build()); 
    }
    @GetMapping("/stats/seats")
    public Flux<FlightSeatStatsDTO> getFlightSeatStats() {
        return service.getFlightSeatStats();
    }

}
