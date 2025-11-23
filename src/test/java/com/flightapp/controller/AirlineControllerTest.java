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
        AddAirlineRequest req = new AddAirlineRequest();
        req.setAirlineName("Indigo");

        Airline mockAirline = new Airline();
        mockAirline.setAirlineName("Indigo");

        when(service.addAirline(req)).thenReturn(Mono.just(mockAirline));

        ResponseEntity<Airline> response = controller.add(req).block();

        assertEquals(201, response.getStatusCode().value());
        assertEquals("Indigo", response.getBody().getAirlineName());
    }

    @Test
    void testGetAllAirlines() {
        when(service.getAll())
                .thenReturn(Flux.just(new Airline(), new Airline()));

        List<Airline> result = controller.all().collectList().block();

        assertEquals(2, result.size());
    }

    @Test
    void testGetByCode_found() {
        Airline a = new Airline();
        a.setAirlineCode("AI");

        when(service.getAirlineByCode("AI"))
                .thenReturn(Mono.just(a));

        ResponseEntity<Airline> response = controller.getByCode("AI").block();

        assertEquals(200, response.getStatusCode().value());
        assertEquals("AI", response.getBody().getAirlineCode());
    }

    @Test
    void testGetByCode_notFound() {
        when(service.getAirlineByCode("XX"))
                .thenReturn(Mono.empty());

        ResponseEntity<Airline> response = controller.getByCode("XX").block();

        assertEquals(404, response.getStatusCode().value());
    }
}
