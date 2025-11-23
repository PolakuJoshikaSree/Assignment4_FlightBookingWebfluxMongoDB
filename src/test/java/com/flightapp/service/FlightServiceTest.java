package com.flightapp.service;

import com.flightapp.model.Airline;
import com.flightapp.model.Flight;
import com.flightapp.repository.AirlineRepository;
import com.flightapp.repository.FlightRepository;
import com.flightapp.request.AddFlightRequest;
import com.flightapp.request.FlightSearchRequest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.time.LocalDate;

class FlightServiceTest {

    @Mock FlightRepository repo;
    @Mock AirlineRepository airlineRepo;

    @InjectMocks FlightService service;

    AddFlightRequest req;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);

        req = new AddFlightRequest();
        req.setFlightNumber("AI101");
        req.setFromPlace("Delhi");
        req.setToPlace("Mumbai");
        req.setFlightDate("2025-01-01");
        req.setDepartureTime("10:00");
        req.setArrivalTime("12:00");
        req.setTotalSeats(180);
        req.setPrice(4500.0);
        req.setBaggageLimitKg(15);
        req.setAirlineCode("AI");
    }

    @Test
    void testAddFlight() {
        Airline airline = new Airline();
        airline.setAirlineCode("AI");

        Mockito.when(airlineRepo.findByAirlineCode("AI"))
                .thenReturn(Mono.just(airline));

        Mockito.when(repo.save(Mockito.any()))
                .thenAnswer(inv -> Mono.just(inv.getArgument(0)));

        StepVerifier.create(service.addFlight(req))
                .expectNextMatches(f -> f.getAirlineCode().equals("AI"))
                .verifyComplete();
    }

    @Test
    void testAddFlight_NoAirlineFound() {
        Mockito.when(airlineRepo.findByAirlineCode("AI"))
                .thenReturn(Mono.empty());

        StepVerifier.create(service.addFlight(req))
                .verifyComplete();
    }

    @Test
    void testSearchFlights() {
        Flight f = new Flight();

        Mockito.when(repo.findByFromPlaceAndToPlaceAndFlightDate(
                "Delhi", "Mumbai", LocalDate.parse("2025-01-01"))
        ).thenReturn(Flux.just(f));

        FlightSearchRequest s = new FlightSearchRequest();
        s.setFromPlace("Delhi");
        s.setToPlace("Mumbai");
        s.setFlightDate("2025-01-01");

        StepVerifier.create(service.searchFlights(s))
                .expectNext(f)
                .verifyComplete();
    }

    @Test
    void testGetFlightById() {
        Flight f = new Flight();

        Mockito.when(repo.findById("F1")).thenReturn(Mono.just(f));

        StepVerifier.create(service.getFlightById("F1"))
                .expectNext(f)
                .verifyComplete();
    }

    @Test
    void testUpdateFlight() {
        Flight existing = new Flight();
        existing.setId("F1");

        Mockito.when(repo.findById("F1")).thenReturn(Mono.just(existing));
        Mockito.when(repo.save(existing)).thenReturn(Mono.just(existing));

        StepVerifier.create(service.updateFlight("F1", req))
                .expectNext(existing)
                .verifyComplete();
    }

    @Test
    void testUpdateFlight_NotFound() {
        Mockito.when(repo.findById("X")).thenReturn(Mono.empty());

        StepVerifier.create(service.updateFlight("X", req))
                .verifyComplete();
    }

    @Test
    void testDeleteFlight() {
        Mockito.when(repo.deleteById("F1")).thenReturn(Mono.empty());

        StepVerifier.create(service.deleteFlight("F1"))
                .verifyComplete();
    }

    @Test
    void testUpdateSeatCount() {
        Flight flight = new Flight();
        flight.setBookedSeats(10);

        Mockito.when(repo.findById("F1")).thenReturn(Mono.just(flight));
        Mockito.when(repo.save(Mockito.any())).thenReturn(Mono.just(flight));

        StepVerifier.create(service.updateSeatCount("F1", 5))
                .verifyComplete();
    }

    @Test
    void testUpdateSeatCount_NotFound() {
        Mockito.when(repo.findById("X")).thenReturn(Mono.empty());

        StepVerifier.create(service.updateSeatCount("X", 5))
                .verifyComplete();
    }
}
