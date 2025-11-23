package com.flightapp.controller;

import com.flightapp.model.Flight;
import com.flightapp.request.AddFlightRequest;
import com.flightapp.request.FlightSearchRequest;
import com.flightapp.service.FlightService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class FlightControllerTest {

    @Mock
    private FlightService service;

    @InjectMocks
    private FlightController controller;

    @Test
    void testAddFlight() {
        // Preparing add request input.
        AddFlightRequest req = new AddFlightRequest();
        req.setFlightNumber("AI101");

        // Fake saved flight.
        Flight saved = new Flight();
        saved.setFlightNumber("AI101");

        when(service.addFlight(req)).thenReturn(Mono.just(saved));

        ResponseEntity<Flight> response = controller.addFlight(req).block();

        // Validating the saved flight response.
        assertEquals(201, response.getStatusCode().value());
        assertEquals("AI101", response.getBody().getFlightNumber());
    }

    @Test
    void testSearchFlights() {
        // Mocking two flights as if DB returned them.
        when(service.searchFlights(any()))
                .thenReturn(Flux.just(new Flight(), new Flight()));

        List<Flight> list = controller.searchFlights(new FlightSearchRequest())
                .collectList()
                .block();

        assertEquals(2, list.size());
    }

    @Test
    void testGetFlight_found() {
        Flight f = new Flight();
        f.setId("999");

        when(service.getFlightById("999")).thenReturn(Mono.just(f));

        ResponseEntity<Flight> response = controller.getFlightById("999").block();

        assertEquals(200, response.getStatusCode().value());
        assertEquals("999", response.getBody().getId());
    }

    @Test
    void testGetFlight_notFound() {
        when(service.getFlightById("NOTFOUND")).thenReturn(Mono.empty());

        ResponseEntity<Flight> response = controller.getFlightById("NOTFOUND").block();

        assertEquals(404, response.getStatusCode().value());
    }
}
