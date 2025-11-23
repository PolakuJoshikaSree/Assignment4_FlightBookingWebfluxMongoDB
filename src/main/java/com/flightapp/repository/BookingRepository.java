package com.flightapp.repository;

import com.flightapp.dto.SeatsPerFlightDTO;
import com.flightapp.dto.BookingCountPerDayDTO;
import com.flightapp.model.Booking;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface BookingRepository extends ReactiveMongoRepository<Booking, String> {

    // Fetches booking using PNR number.
    Mono<Booking> findByPnr(String pnr);

    // Gets all bookings made by an email, sorted by recent first.
    Flux<Booking> findByEmailOrderByBookingTimeDesc(String email);

    //calculates total seats booked for each flight by status.
    @Aggregation(pipeline = {
            "{ $match: { status: ?0 } }",
            "{ $group: { _id: '$flightId', totalSeatsBooked: { $sum: '$seatsBooked' } } }",
            "{ $project: { _id: 0, flightId: '$_id', totalSeatsBooked: 1 } }"
    })
    Flux<SeatsPerFlightDTO> getSeatsBookedGroupedByFlight(String status);

    //counts total bookings per day.
    @Aggregation(pipeline = {
            "{ $group: { _id: { $dateToString: { format: '%Y-%m-%d', date: '$bookingTime' } }, totalBookings: { $sum: 1 } } }",
            "{ $project: { _id: 0, date: '$_id', totalBookings: 1 } }",
            "{ $sort: { date: 1 } }"
    })
    Flux<BookingCountPerDayDTO> getDailyBookingCount();
}
