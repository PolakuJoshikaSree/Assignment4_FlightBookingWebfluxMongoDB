package com.flightapp.repository;

import com.flightapp.dto.FlightSeatStatsDTO;
import com.flightapp.model.Flight;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;

import java.time.LocalDate;

public interface FlightRepository extends ReactiveMongoRepository<Flight, String> {

    Flux<Flight> findByFromPlaceAndToPlaceAndFlightDate(String fromPlace, String toPlace, LocalDate flightDate);

    //Remaining seats = totalSeats - bookedSeats
    @Aggregation(pipeline = {
            "{ $lookup: { from: 'bookings', localField: '_id', foreignField: 'flightId', as: 'bookings' } }",
            "{ $addFields: { totalSeatsBooked: { $sum: '$bookings.seatsBooked' } } }",
            "{ $project: { flightNumber: 1, fromPlace: 1, toPlace: 1, flightDate: 1, totalSeats: 1, " +
                    "totalSeatsBooked: 1, remainingSeats: { $subtract: ['$totalSeats', '$totalSeatsBooked'] } } }"
    })
    Flux<FlightSeatStatsDTO> getFlightSeatStats();
}
