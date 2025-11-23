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

    private final BookingRepository bookingRepo;
    private final PassengerRepository passengerRepo;
    private final FlightService flightService;
    
    public Mono<Booking> book(String flightId, BookingRequest req) {
        return flightService.getFlightById(flightId)
                .flatMap(flight -> {

                    Booking b = new Booking();
                    b.setPnr(UUID.randomUUID().toString().substring(0, 8).toUpperCase());
                    b.setEmail(req.getEmail());
                    b.setPrimaryPassenger(req.getPrimaryPassenger());
                    b.setSeatsBooked(req.getSeats());
                    b.setBookingTime(LocalDateTime.now());
                    b.setStatus(BookingStatus.CONFIRMED.name());
                    b.setFlightId(flightId);

                    return bookingRepo.save(b)
                            .flatMap(saved ->
                                    passengerRepo.saveAll(
                                            req.getPassengers()
                                                    .stream()
                                                    .map(p -> {
                                                        Passenger ps = new Passenger();
                                                        ps.setPassengerName(p.getName());
                                                        ps.setAge(p.getAge());
                                                        ps.setGender(p.getGender().name());
                                                        ps.setMealPreference(
                                                                p.getMealPreference() != null
                                                                        ? p.getMealPreference().name()
                                                                        : null
                                                        );
                                                        ps.setLuggageWeight(p.getLuggageWeight());
                                                        ps.setBookingId(saved.getId());  // Mongo String ID
                                                        return ps;
                                                    }).toList()
                                    ).then(Mono.just(saved)));
                });
    }
    public Mono<Booking> getBooking(String pnr) {
        return bookingRepo.findByPnr(pnr);
    }
    public Mono<Void> cancelBooking(String pnr) {
        return bookingRepo.findByPnr(pnr)
                .flatMap(b -> {
                    b.setStatus(BookingStatus.CANCELLED.name());
                    return bookingRepo.save(b);
                })
                .then();
    }
    public Mono<Void> deleteBooking(String pnr) {
        return bookingRepo.findByPnr(pnr)
                .flatMap(bookingRepo::delete);
    }
    public Flux<SeatsPerFlightDTO> getSeatsBooked(String status) {
        return bookingRepo.getSeatsBookedGroupedByFlight(status);
    }
    public Flux<BookingCountPerDayDTO> getDailyBookingCount() {
        return bookingRepo.getDailyBookingCount();
    }
    public Flux<PassengerCountDTO> getPassengerCountPerFlight() {
        return passengerRepo.getPassengerCountPerFlight();
    }
    

}
