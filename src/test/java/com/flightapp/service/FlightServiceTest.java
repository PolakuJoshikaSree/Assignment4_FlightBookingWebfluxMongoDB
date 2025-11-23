package com.flightapp.service;

import com.flightapp.model.Airline;
import com.flightapp.model.Flight;
import com.flightapp.repository.AirlineRepository;
import com.flightapp.repository.FlightRepository;
import com.flightapp.request.AddFlightRequest;
import com.flightapp.request.FlightSearchRequest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class FlightServiceTest {

    @Mock
    private FlightRepository flightRepo;

    @Mock
    private AirlineRepository airlineRepo;

    @InjectMocks
    private FlightService service;

    @Test
    void testAddFlight() {
        // Preparing a complete AddFlightRequest object.
        AddFlightRequest req = new AddFlightRequest();
        req.setAirlineCode("AI101");
        req.setFlightNumber("AI-10");
        req.setFromPlace("Delhi");
        req.setToPlace("Mumbai");
        req.setFlightDate("2025-12-01");
        req.setDepartureTime("10:00");
        req.setArrivalTime("12:00");
        req.setTotalSeats(180);
        req.setPrice(5000.0);
        req.setBaggageLimitKg(15);

        // Mocking airline verification before saving a flight.
        Airline airline = new Airline();
        airline.setAirlineCode("AI101");

        when(airlineRepo.findByAirlineCode("AI101"))
                .thenReturn(Mono.just(airline));

        // Mock saving of flight.
        when(flightRepo.save(any(Flight.class)))
                .thenReturn(Mono.just(new Flight()));

        Flight result = service.addFlight(req).block();

        // Just checking that something is returned.
        assertNotNull(result);
    }

    @Test
    void testSearchFlights() {
        // Creating a request as the UI would send it.
        FlightSearchRequest req = new FlightSearchRequest();
        req.setFromPlace("Delhi");
        req.setToPlace("Mumbai");
        req.setFlightDate("2025-12-01");

        // Mocking empty flights for that route.
        when(flightRepo.findByFromPlaceAndToPlaceAndFlightDate(
                "Delhi", "Mumbai", LocalDate.parse("2025-12-01")
        )).thenReturn(Flux.empty());

        var result = service.searchFlights(req).collectList().block();

        // Since mocked empty — expecting 0 results.
        assertEquals(0, result.size());
    }
}
