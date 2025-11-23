package com.flightapp.controller;

import com.flightapp.model.Airline;
import com.flightapp.request.AddAirlineRequest;
import com.flightapp.service.AirlineService;
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
class AirlineControllerTest {

    @Mock
    private AirlineService service;

    @InjectMocks
    private AirlineController controller;

    @Test
    void testAddAirline() {
        // Creating the request object that the API receives.
        AddAirlineRequest req = new AddAirlineRequest();
        req.setAirlineName("Indigo");

        // Mock airline returned from the service after save.
        Airline mockAirline = new Airline();
        mockAirline.setAirlineName("Indigo");

        // Mocking service behavior for insertion.
        when(service.addAirline(req)).thenReturn(Mono.just(mockAirline));

        ResponseEntity<Airline> response = controller.add(req).block();

        // Verifying that the controller returned correct status + body content.
        assertEquals(201, response.getStatusCode().value());
        assertEquals("Indigo", response.getBody().getAirlineName());
    }

    @Test
    void testGetAllAirlines() {
        // Mock two airline entries like DB results.
        when(service.getAll())
                .thenReturn(Flux.just(new Airline(), new Airline()));

        List<Airline> result = controller.all().collectList().block();

        // Controller should return both items.
        assertEquals(2, result.size());
    }

    @Test
    void testGetByCode_found() {
        Airline a = new Airline();
        a.setAirlineCode("AI");

        when(service.getAirlineByCode("AI"))
                .thenReturn(Mono.just(a));

        ResponseEntity<Airline> response = controller.getByCode("AI").block();

        // Expecting HTTP 200 and correct airline code.
        assertEquals(200, response.getStatusCode().value());
        assertEquals("AI", response.getBody().getAirlineCode());
    }

    @Test
    void testGetByCode_notFound() {
        // Simulation of service returning nothing.
        when(service.getAirlineByCode("XX"))
                .thenReturn(Mono.empty());

        ResponseEntity<Airline> response = controller.getByCode("XX").block();

        // Not found should return 404.
        assertEquals(404, response.getStatusCode().value());
    }
}
