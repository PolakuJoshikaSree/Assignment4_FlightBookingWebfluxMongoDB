package com.flightapp.controller;

import com.flightapp.dto.FlightSeatStatsDTO;
import com.flightapp.model.Flight;
import com.flightapp.request.AddFlightRequest;
import com.flightapp.request.FlightSearchRequest;
import com.flightapp.service.FlightService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@WebFluxTest(FlightController.class)
class FlightControllerTest {

    @Autowired
    private WebTestClient client;

    @MockBean
    private FlightService service;

    @Test
    void testAddFlight() {
        AddFlightRequest rq = new AddFlightRequest();
        rq.setFlightNumber("AI101");

        Flight f = new Flight();
        f.setId("F1");

        Mockito.when(service.addFlight(Mockito.any())).thenReturn(Mono.just(f));

        client.post().uri("/api/flights/add")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(rq)
                .exchange()
                .expectStatus().isCreated();
    }

    @Test
    void testSearch() {
        FlightSearchRequest rq = new FlightSearchRequest();
        rq.setFromPlace("Delhi");
        rq.setToPlace("Mumbai");
        rq.setFlightDate("2025-01-01");

        Mockito.when(service.searchFlights(Mockito.any()))
                .thenReturn(Flux.just(new Flight()));

        client.post().uri("/api/flights/search")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(rq)
                .exchange()
                .expectStatus().isOk();
    }

    @Test
    void testGetById() {
        Flight f = new Flight();
        f.setId("F1");

        Mockito.when(service.getFlightById("F1"))
                .thenReturn(Mono.just(f));

        client.get().uri("/api/flights/F1")
                .exchange()
                .expectStatus().isOk();
    }

    @Test
    void testSeatStats() {
        Mockito.when(service.getFlightSeatStats())
                .thenReturn(Flux.just(new FlightSeatStatsDTO()));

        client.get().uri("/api/flights/stats/seats")
                .exchange()
                .expectStatus().isOk();
    }
}
