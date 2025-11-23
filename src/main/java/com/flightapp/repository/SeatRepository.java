package com.flightapp.repository;

import com.flightapp.model.Seat;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;

public interface SeatRepository extends ReactiveMongoRepository<Seat, String> {

    // Returns all seats belonging to a flight.
    Flux<Seat> findByFlightId(String flightId);

    // Returns seats filtered by their status .
    Flux<Seat> findByFlightIdAndStatus(String flightId, String status);
}
