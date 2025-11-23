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

    // Handles flight operations such as adding, searching, and statistics
    private final FlightService service;

    // Adds a new flight after validating airline code availability.
    @PostMapping("/add")
    public Mono<ResponseEntity<Flight>> addFlight(@RequestBody AddFlightRequest req) {
        return service.addFlight(req)
                .map(saved -> ResponseEntity.status(201).body(saved));
    }

    // Searches flights based on source, destination, and date.
    @PostMapping("/search")
    public Flux<Flight> searchFlights(@RequestBody FlightSearchRequest req) {
        return service.searchFlights(req);
    }

    // Gets full details of a single flight using its ID.
    @GetMapping("/{id}")
    public Mono<ResponseEntity<Flight>> getFlightById(@PathVariable String id) {
        return service.getFlightById(id)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    // Shows remaining/booked seats for all flights ...extension of aggregation.
    @GetMapping("/stats/seats")
    public Flux<FlightSeatStatsDTO> getFlightSeatStats() {
        return service.getFlightSeatStats();
    }
}
