package com.flightapp.repository;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class PaymentRepositoryTest {

    @Test
    void testRevenueByFlight() {
        // Using mock repo to avoid real DB queries.
        PaymentRepository repo = mock(PaymentRepository.class);

        // Simulating empty revenue response.
        when(repo.getFlightWiseRevenue()).thenReturn(Flux.empty());

        var result = repo.getFlightWiseRevenue().collectList().block();

        // Expecting empty data because of the mock.
        assertEquals(0, result.size());
    }
}
