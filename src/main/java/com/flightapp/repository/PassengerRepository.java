package com.flightapp.repository;

import com.flightapp.dto.PassengerCountDTO;
import com.flightapp.model.Passenger;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;

public interface PassengerRepository extends ReactiveMongoRepository<Passenger, String> {

    //Passenger count per flight
    @Aggregation(pipeline = {
            "{ $group: { _id: '$bookingId', totalPassengers: { $sum: 1 } } }",
            "{ $project: { _id: 0, flightId: '$_id', totalPassengers: 1 } }"
    })
    Flux<PassengerCountDTO> getPassengerCountPerFlight();
}
