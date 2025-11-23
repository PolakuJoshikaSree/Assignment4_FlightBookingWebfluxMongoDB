package com.flightapp.service;

import com.flightapp.model.Airline;
import com.flightapp.repository.AirlineRepository;
import com.flightapp.request.AddAirlineRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

class AirlineServiceTest {

    private AirlineRepository repo;
    private AirlineService service;

    @BeforeEach
    void setup() {
        repo = Mockito.mock(AirlineRepository.class);
        service = new AirlineService(repo);
    }

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

        Mockito.when(repo.save(Mockito.any())).thenReturn(Mono.just(saved));

        StepVerifier.create(service.addAirline(req))
                .expectNextMatches(a -> a.getAirlineCode().equals("6E"))
                .verifyComplete();
    }

    @Test
    void testGetAllAirlines() {
        Airline a1 = new Airline(); a1.setId("A1");
        Airline a2 = new Airline(); a2.setId("A2");

        Mockito.when(repo.findAll()).thenReturn(Flux.just(a1, a2));

        StepVerifier.create(service.getAll())
                .expectNext(a1)
                .expectNext(a2)
                .verifyComplete();
    }

    @Test
    void testGetAirlineByCode() {
        Airline a = new Airline();
        a.setAirlineCode("6E");

        Mockito.when(repo.findByAirlineCode("6E")).thenReturn(Mono.just(a));

        StepVerifier.create(service.getAirlineByCode("6E"))
                .expectNext(a)
                .verifyComplete();
    }

    @Test
    void testUpdateAirline() {
        Airline existing = new Airline();
        existing.setAirlineCode("6E");
        existing.setAirlineName("Old");
        existing.setCountry("Old");

        AddAirlineRequest req = new AddAirlineRequest();
        req.setAirlineName("New");
        req.setCountry("India");

        Mockito.when(repo.findByAirlineCode("6E")).thenReturn(Mono.just(existing));
        Mockito.when(repo.save(existing)).thenReturn(Mono.just(existing));

        StepVerifier.create(service.updateAirline("6E", req))
                .expectNextMatches(a -> a.getAirlineName().equals("New"))
                .verifyComplete();
    }

    @Test
    void testDeleteAirline() {
        Airline existing = new Airline();
        existing.setAirlineCode("6E");

        Mockito.when(repo.findByAirlineCode("6E")).thenReturn(Mono.just(existing));
        Mockito.when(repo.delete(existing)).thenReturn(Mono.empty());

        StepVerifier.create(service.deleteAirline("6E"))
                .verifyComplete();
    }

    @Test
    void testDeleteAirlineById() {
        Mockito.when(repo.deleteById("A1")).thenReturn(Mono.empty());

        StepVerifier.create(service.deleteAirlineById("A1"))
                .verifyComplete();
    }

    // Negative scenarios
    @Test
    void testUpdateAirline_NotFound() {
        Mockito.when(repo.findByAirlineCode("XX")).thenReturn(Mono.empty());

        StepVerifier.create(service.updateAirline("XX", new AddAirlineRequest()))
                .verifyComplete();
    }

    @Test
    void testDeleteAirline_NotFound() {
        Mockito.when(repo.findByAirlineCode("XX")).thenReturn(Mono.empty());

        StepVerifier.create(service.deleteAirline("XX"))
                .verifyComplete();
    }
}
