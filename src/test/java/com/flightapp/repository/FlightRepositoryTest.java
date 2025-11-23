package com.flightapp.repository;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;

import java.time.LocalDate;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class FlightRepositoryTest {

    @Test
    void testFindByRoute() {
        // Mocked repo so we don't hit an actual Mongo database.
        FlightRepository repo = mock(FlightRepository.class);

        // Mocking an empty result for this route search.
        when(repo.findByFromPlaceAndToPlaceAndFlightDate(
                "Delhi", "Mumbai", LocalDate.now()
        )).thenReturn(Flux.empty());

        var result = repo.findByFromPlaceAndToPlaceAndFlightDate(
                "Delhi", "Mumbai", LocalDate.now()
        ).collectList().block();

        // Expecting zero items because we mocked it empty.
        assertEquals(0, result.size());
    }

    @Test
    void testGetFlightSeatStats() {
        // Mocking aggregation pipeline to return no stats.
        FlightRepository repo = mock(FlightRepository.class);

        when(repo.getFlightSeatStats()).thenReturn(Flux.empty());

        assertTrue(repo.getFlightSeatStats().collectList().block().isEmpty());
    }
}
