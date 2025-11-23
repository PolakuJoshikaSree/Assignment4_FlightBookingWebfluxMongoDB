package com.flightapp.service;

import com.flightapp.dto.BookingCountPerDayDTO;
import com.flightapp.dto.PassengerCountDTO;
import com.flightapp.dto.SeatsPerFlightDTO;
import com.flightapp.model.Booking;
import com.flightapp.model.Passenger;
import com.flightapp.model.enums.BookingStatus;
import com.flightapp.repository.BookingRepository;
import com.flightapp.repository.PassengerRepository;
import com.flightapp.request.BookingRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class BookingService {

    // Booking and passenger repositories used for saving data.
    private final BookingRepository bookingRepo;
    private final PassengerRepository passengerRepo;

    // Service to validate and fetch flight details.
    private final FlightService flightService;

    // Creates a booking and stores passenger information together.
    public Mono<Booking> book(String flightId, BookingRequest req) {
        return flightService.getFlightById(flightId)
                .flatMap(flight -> {

                    // Create booking entry.
                    Booking b = new Booking();
                    b.setPnr(UUID.randomUUID().toString().substring(0, 8).toUpperCase());
                    b.setEmail(req.getEmail());
                    b.setPrimaryPassenger(req.getPrimaryPassenger());
                    b.setSeatsBooked(req.getSeats());
                    b.setBookingTime(LocalDateTime.now());
                    b.setStatus(BookingStatus.CONFIRMED.name());
                    b.setFlightId(flightId);

                    // Save booking first, then save passengers.
                    return bookingRepo.save(b)
                            .flatMap(saved ->
                                    passengerRepo.saveAll(
                                            req.getPassengers().stream().map(p -> {
                                                Passenger ps = new Passenger();
                                                ps.setPassengerName(p.getName());
                                                ps.setAge(p.getAge());
                                                ps.setGender(p.getGender().name());
                                                ps.setMealPreference(p.getMealPreference() != null ? p.getMealPreference().name() : null);
                                                ps.setLuggageWeight(p.getLuggageWeight());
                                                ps.setBookingId(saved.getId());
                                                return ps;
                                            }).toList()
                                    ).then(Mono.just(saved))
                            );
                });
    }

    // Fetch booking based on PNR.
    public Mono<Booking> getBooking(String pnr) {
        return bookingRepo.findByPnr(pnr);
    }

    // Cancels a booking by updating its status.
    public Mono<Void> cancelBooking(String pnr) {
        return bookingRepo.findByPnr(pnr)
                .flatMap(b -> {
                    b.setStatus(BookingStatus.CANCELLED.name());
                    return bookingRepo.save(b);
                })
                .then();
    }

    // Deletes booking permanently.
    public Mono<Void> deleteBooking(String pnr) {
        return bookingRepo.findByPnr(pnr)
                .flatMap(bookingRepo::delete);
    }

    //seats booked grouped by flight...extension of aggregation.
    public Flux<SeatsPerFlightDTO> getSeatsBooked(String status) {
        return bookingRepo.getSeatsBookedGroupedByFlight(status);
    }

    //booking count grouped by date...extension of aggregation.
    public Flux<BookingCountPerDayDTO> getDailyBookingCount() {
        return bookingRepo.getDailyBookingCount();
    }

    //passenger count for each flight...extension of aggregation.
    public Flux<PassengerCountDTO> getPassengerCountPerFlight() {
        return passengerRepo.getPassengerCountPerFlight();
    }
}
