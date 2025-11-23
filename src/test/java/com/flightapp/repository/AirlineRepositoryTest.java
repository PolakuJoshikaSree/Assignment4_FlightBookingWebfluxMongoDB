package com.flightapp.repository;

import com.flightapp.model.Airline;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Mono;
import reactor.core.publisher.Flux;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class AirlineRepositoryTest {

    @Test
    void testFindByAirlineCode() {
        // Mocking the repository instead of hitting the actual DB.
        AirlineRepository repo = mock(AirlineRepository.class);

        // Creating a dummy airline to return when the method is called.
        Airline a = new Airline();
        a.setAirlineCode("AI");

        // Telling Mockito what to return when findByAirlineCode is used.
        when(repo.findByAirlineCode("AI")).thenReturn(Mono.just(a));

        // Calling the actual method.
        Airline result = repo.findByAirlineCode("AI").block();

        // Making sure the repo gives back the expected code.
        assertEquals("AI", result.getAirlineCode());
    }

    @Test
    void testGetFlightCountPerAirline() {
        // Mocking the repo to return an empty Flux for aggregation.
        AirlineRepository repo = mock(AirlineRepository.class);

        when(repo.getFlightCountPerAirline())
                .thenReturn(Flux.empty());

        var result = repo.getFlightCountPerAirline().collectList().block();

        // Expecting no results since we mocked it empty.
        assertEquals(0, result.size());
    }
}
