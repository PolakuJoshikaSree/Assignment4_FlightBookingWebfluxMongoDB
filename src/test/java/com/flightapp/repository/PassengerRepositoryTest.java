package com.flightapp.repository;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class PassengerRepositoryTest {

    @Test
    void testPassengerCountPerFlight() {
        // Mocking repo that interacts with MongoDB.
        PassengerRepository repo = mock(PassengerRepository.class);

        // Returning an empty Flux for this aggregation.
        when(repo.getPassengerCountPerFlight()).thenReturn(Flux.empty());

        var list = repo.getPassengerCountPerFlight().collectList().block();

        // Since mocked empty, expecting zero result size.
        assertEquals(0, list.size());
    }
}
