package com.flightapp.request;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class BookingRequestTest {

    @Test
    void testBookingRequest() {
        BookingRequest req = new BookingRequest();

        req.setEmail("user@mail.com");
        req.setPrimaryPassenger("John Doe");
        req.setSeats(2);

        assertEquals("user@mail.com", req.getEmail());
        assertEquals("John Doe", req.getPrimaryPassenger());
        assertEquals(2, req.getSeats());
    }
}
