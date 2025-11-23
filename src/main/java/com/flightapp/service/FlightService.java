package com.flightapp.service;

import com.flightapp.dto.FlightSeatStatsDTO;
import com.flightapp.model.Flight;
import com.flightapp.request.AddFlightRequest;
import com.flightapp.request.FlightSearchRequest;
import com.flightapp.repository.AirlineRepository;
import com.flightapp.repository.FlightRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDate;
import java.time.LocalTime;

@Service
@RequiredArgsConstructor
public class FlightService {

    private final FlightRepository repo;
    private final AirlineRepository airlineRepo;

    public Mono<Flight> addFlight(AddFlightRequest req) {

        return airlineRepo.findByAirlineCode(req.getAirlineCode())
                .flatMap(airline -> {

                    Flight f = new Flight();
                    f.setFlightNumber(req.getFlightNumber());
                    f.setFromPlace(req.getFromPlace());
                    f.setToPlace(req.getToPlace());
                    f.setFlightDate(LocalDate.parse(req.getFlightDate()));
                    f.setDepartureTime(LocalTime.parse(req.getDepartureTime()));
                    f.setArrivalTime(LocalTime.parse(req.getArrivalTime()));
                    f.setTotalSeats(req.getTotalSeats());
                    f.setBookedSeats(0);
                    f.setPrice(req.getPrice());
                    f.setBaggageLimitKg(req.getBaggageLimitKg());
                    f.setAirlineCode(airline.getAirlineCode());

                    return repo.save(f);
                });
    }

    public Flux<Flight> searchFlights(FlightSearchRequest req) {
        return repo.findByFromPlaceAndToPlaceAndFlightDate(
                req.getFromPlace(),
                req.getToPlace(),
                LocalDate.parse(req.getFlightDate())
        );
    }


    public Mono<Flight> getFlightById(String id) {
        return repo.findById(id);
    }


    public Mono<Flight> updateFlight(String id, AddFlightRequest req) {
        return repo.findById(id)
                .flatMap(existing -> {
                    existing.setFlightNumber(req.getFlightNumber());
                    existing.setFromPlace(req.getFromPlace());
                    existing.setToPlace(req.getToPlace());
                    existing.setFlightDate(LocalDate.parse(req.getFlightDate()));
                    existing.setDepartureTime(LocalTime.parse(req.getDepartureTime()));
                    existing.setArrivalTime(LocalTime.parse(req.getArrivalTime()));
                    existing.setTotalSeats(req.getTotalSeats());
                    existing.setPrice(req.getPrice());
                    existing.setBaggageLimitKg(req.getBaggageLimitKg());
                    return repo.save(existing);
                });
    }


    public Mono<Void> deleteFlight(String id) {
        return repo.deleteById(id);
    }


    public Mono<Void> updateSeatCount(String flightId, int seatsToBook) {
        return repo.findById(flightId)
                .flatMap(flight -> {
                    flight.setBookedSeats(flight.getBookedSeats() + seatsToBook);
                    return repo.save(flight);
                })
                .then();
    }
    public Flux<FlightSeatStatsDTO> getFlightSeatStats() {
        return repo.getFlightSeatStats();
    }

}
