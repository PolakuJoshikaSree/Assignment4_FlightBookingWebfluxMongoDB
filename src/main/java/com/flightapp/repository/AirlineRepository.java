package com.flightapp.repository;

import com.flightapp.dto.FlightCountPerAirlineDTO;
import com.flightapp.model.Airline;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface AirlineRepository extends ReactiveMongoRepository<Airline, String> {

    // Finds an airline using its unique code .
    Mono<Airline> findByAirlineCode(String airlineCode);

    // Aggregation: counts how many flights belong to each airline.
    @Aggregation(pipeline = {
            "{ $lookup: { from: 'flights', localField: 'airlineCode', foreignField: 'airlineCode', as: 'flights' } }",
            "{ $project: { airlineCode: 1, airlineName: 1, totalFlights: { $size: '$flights' } } }"
    })
    Flux<FlightCountPerAirlineDTO> getFlightCountPerAirline();
}
