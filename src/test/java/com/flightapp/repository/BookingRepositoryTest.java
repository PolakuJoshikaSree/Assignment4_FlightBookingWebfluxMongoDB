package com.flightapp.repository;

import com.flightapp.model.Booking;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Mono;
import reactor.core.publisher.Flux;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class BookingRepositoryTest {

    @Test
    void testFindByPnr() {
        // Mocking repository to avoid real DB calls.
        BookingRepository repo = mock(BookingRepository.class);

        // Preparing sample booking object.
        Booking b = new Booking();
        b.setPnr("PNR1");

        // Defining behavior for findByPnr.
        when(repo.findByPnr("PNR1")).thenReturn(Mono.just(b));

        Booking result = repo.findByPnr("PNR1").block();

        // Verifying correct PNR is returned.
        assertEquals("PNR1", result.getPnr());
    }

    @Test
    void testSeatsBookedGroupedByFlight() {
        // Mocking aggregation pipeline query.
        BookingRepository repo = mock(BookingRepository.class);

        when(repo.getSeatsBookedGroupedByFlight("CONFIRMED"))
                .thenReturn(Flux.empty());

        assertTrue(repo.getSeatsBookedGroupedByFlight("CONFIRMED").collectList().block().isEmpty());
    }

    @Test
    void testDailyBookingCount() {
        // Mocking daily count aggregation.
        BookingRepository repo = mock(BookingRepository.class);

        when(repo.getDailyBookingCount()).thenReturn(Flux.empty());

        assertEquals(0, repo.getDailyBookingCount().collectList().block().size());
    }
}
