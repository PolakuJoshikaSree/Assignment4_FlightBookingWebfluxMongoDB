package com.flightapp.repository;

import com.flightapp.dto.FlightRevenueDTO;
import com.flightapp.model.Payment;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;

public interface PaymentRepository extends ReactiveMongoRepository<Payment, String> {

    //Total revenue per flight
    @Aggregation(pipeline = {
            "{ $lookup: { from: 'bookings', localField: 'bookingId', foreignField: '_id', as: 'booking' } }",
            "{ $unwind: '$booking' }",
            "{ $group: { _id: '$booking.flightId', totalRevenue: { $sum: '$amount' } } }",
            "{ $project: { _id: 0, flightId: '$_id', totalRevenue: 1 } }"
    })
    Flux<FlightRevenueDTO> getFlightWiseRevenue();
}
