package com.flightapp.controller;

import com.flightapp.dto.BookingCountPerDayDTO;
import com.flightapp.dto.PassengerCountDTO;
import com.flightapp.dto.SeatsPerFlightDTO;
import com.flightapp.model.Booking;
import com.flightapp.request.BookingRequest;
import com.flightapp.service.BookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/bookings")
public class BookingController {

    // BookingService handles all booking operations and DB interactions.
    private final BookingService service;

    // Creates a new booking for a specific flight. Generates PNR internally.
    @PostMapping("/book/{flightId}")
    public Mono<ResponseEntity<Booking>> book(
            @PathVariable String flightId,
            @RequestBody BookingRequest req) {

        return service.book(flightId, req)
                .map(saved -> ResponseEntity.status(201).body(saved));
    }

    // Fetches booking details using PNR. Returns 404 if not found.
    @GetMapping("/{pnr}")
    public Mono<ResponseEntity<Booking>> getBooking(@PathVariable String pnr) {
        return service.getBooking(pnr)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    // Deletes a booking by PNR. This physically removes the booking from MongoDB.
    @DeleteMapping("/{pnr}")
    public Mono<ResponseEntity<Void>> deleteBooking(@PathVariable String pnr) {
        return service.deleteBooking(pnr)
                .then(Mono.just(ResponseEntity.noContent().build()));
    }

    // Aggregation: Returns seats booked per flight filtered by booking status.
    @GetMapping("/stats/seats/{status}")
    public Flux<SeatsPerFlightDTO> getSeatStats(@PathVariable String status) {
        return service.getSeatsBooked(status);
    }

    // Aggregation: Returns booking count grouped by date.
    @GetMapping("/stats/daily")
    public Flux<BookingCountPerDayDTO> getDailyBookingStats() {
        return service.getDailyBookingCount();
    }

    // Aggregation: Returns passenger count for each flight.
    @GetMapping("/stats/passengers")
    public Flux<PassengerCountDTO> getPassengerStats() {
        return service.getPassengerCountPerFlight();
    }
}
