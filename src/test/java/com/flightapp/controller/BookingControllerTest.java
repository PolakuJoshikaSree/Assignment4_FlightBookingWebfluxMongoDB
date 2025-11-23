package com.flightapp.controller;

import com.flightapp.dto.SeatsPerFlightDTO;
import com.flightapp.model.Booking;
import com.flightapp.request.BookingRequest;
import com.flightapp.service.BookingService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

@WebFluxTest(BookingController.class)
class BookingControllerTest {

    @Autowired
    private WebTestClient client;

    @MockBean
    private BookingService service;

    @Test
    void testBook() {
        BookingRequest req = new BookingRequest();
        req.setEmail("x@mail.com");
        req.setPrimaryPassenger("John");
        req.setSeats(1);

        Booking saved = new Booking();
        saved.setId("B1");
        saved.setPnr("PNR123");
        saved.setBookingTime(LocalDateTime.now());

        Mockito.when(service.book(Mockito.eq("F100"), Mockito.any()))
                .thenReturn(Mono.just(saved));

        client.post().uri("/api/bookings/book/F100")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(req)
                .exchange()
                .expectStatus().isCreated()
                .expectBody()
                .jsonPath("$.id").isEqualTo("B1");
    }

    @Test
    void testGetBooking() {
        Booking b = new Booking();
        b.setId("B1");

        Mockito.when(service.getBooking("PNR1")).thenReturn(Mono.just(b));

        client.get().uri("/api/bookings/PNR1")
                .exchange()
                .expectStatus().isOk();
    }

    @Test
    void testDeleteBooking() {
        Mockito.when(service.deleteBooking("P1")).thenReturn(Mono.empty());

        client.delete().uri("/api/bookings/P1")
                .exchange()
                .expectStatus().isNoContent();
    }

    @Test
    void testStats() {
        Mockito.when(service.getSeatsBooked("CONFIRMED"))
                .thenReturn(Flux.just(new SeatsPerFlightDTO()));

        client.get().uri("/api/bookings/stats/seats/CONFIRMED")
                .exchange()
                .expectStatus().isOk();
    }
}
