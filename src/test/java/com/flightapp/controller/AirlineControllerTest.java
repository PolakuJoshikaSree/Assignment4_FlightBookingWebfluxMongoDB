package com.flightapp.controller;

import com.flightapp.model.Airline;
import com.flightapp.request.AddAirlineRequest;
import com.flightapp.service.AirlineService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@WebFluxTest(AirlineController.class)
class AirlineControllerTest {

    @Autowired
    private WebTestClient client;

    @MockBean
    private AirlineService service;

    @Test
    void testAddAirline() {
        AddAirlineRequest req = new AddAirlineRequest();
        req.setAirlineName("Indigo");
        req.setAirlineCode("6E");
        req.setCountry("India");

        Airline saved = new Airline();
        saved.setId("A1");
        saved.setAirlineName("Indigo");
        saved.setAirlineCode("6E");
        saved.setCountry("India");

        Mockito.when(service.addAirline(Mockito.any())).thenReturn(Mono.just(saved));

        client.post().uri("/api/airlines/add")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(req)
                .exchange()
                .expectStatus().isCreated()
                .expectBody()
                .jsonPath("$.airlineName").isEqualTo("Indigo");
    }

    @Test
    void testGetAll() {
        Airline a = new Airline();
        a.setId("A1");
        a.setAirlineName("Air India");

        Mockito.when(service.getAll()).thenReturn(Flux.just(a));

        client.get().uri("/api/airlines/all")
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$[0].airlineName").isEqualTo("Air India");
    }

    @Test
    void testGetByCode() {
        Airline a = new Airline();
        a.setAirlineCode("AI");

        Mockito.when(service.getAirlineByCode("AI")).thenReturn(Mono.just(a));

        client.get().uri("/api/airlines/AI")
                .exchange()
                .expectStatus().isOk();
    }

    @Test
    void testDelete() {
        Mockito.when(service.deleteAirlineById("A1")).thenReturn(Mono.empty());

        client.delete().uri("/api/airlines/A1")
                .exchange()
                .expectStatus().isNoContent();
    }
}
