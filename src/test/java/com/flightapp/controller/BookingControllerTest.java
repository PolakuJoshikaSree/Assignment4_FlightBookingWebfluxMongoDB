package com.flightapp.controller;

import com.flightapp.model.Booking;
import com.flightapp.request.BookingRequest;
import com.flightapp.service.BookingService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import reactor.core.publisher.Mono;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BookingControllerTest {

    @Mock
    private BookingService service;

    @InjectMocks
    private BookingController controller;

    @Test
    void testBookFlight() {
        BookingRequest req = new BookingRequest();
        req.setEmail("user@gmail.com");

        Booking b = new Booking();
        b.setPnr("PNR123");

        when(service.book("FL123", req)).thenReturn(Mono.just(b));

        ResponseEntity<Booking> response = controller.book("FL123", req).block();

        assertEquals(201, response.getStatusCode().value());
        assertEquals("PNR123", response.getBody().getPnr());
    }

    @Test
    void testGetBooking_found() {
        Booking b = new Booking();
        b.setPnr("PNR999");

        when(service.getBooking("PNR999")).thenReturn(Mono.just(b));

        ResponseEntity<Booking> response = controller.getBooking("PNR999").block();

        assertEquals(200, response.getStatusCode().value());
        assertEquals("PNR999", response.getBody().getPnr());
    }

    @Test
    void testGetBooking_notFound() {
        when(service.getBooking("BAD")).thenReturn(Mono.empty());

        ResponseEntity<Booking> response = controller.getBooking("BAD").block();

        assertEquals(404, response.getStatusCode().value());
    }

    @Test
    void testDeleteBooking() {
        when(service.deleteBooking("PNR111")).thenReturn(Mono.empty());

        ResponseEntity<Void> response = controller.deleteBooking("PNR111").block();

        assertEquals(204, response.getStatusCode().value());
    }
}
