package com.flightapp.service;

import com.flightapp.model.Booking;
import com.flightapp.model.Passenger;
import com.flightapp.model.enums.Gender;
import com.flightapp.repository.BookingRepository;
import com.flightapp.repository.PassengerRepository;
import com.flightapp.request.BookingRequest;
import com.flightapp.request.PassengerRequest;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BookingServiceTest {

    @Mock
    private BookingRepository bookingRepo;

    @Mock
    private PassengerRepository passengerRepo;

    @Mock
    private FlightService flightService;

    @InjectMocks
    private BookingService service;

    @Test
    void testBook() {
        // Mocking the flight check — booking cannot proceed without a valid flight.
        when(flightService.getFlightById("F123"))
                .thenReturn(Mono.just(new com.flightapp.model.Flight()));

        // Mocking saved booking that MongoDB would return.
        Booking savedBooking = new Booking();
        savedBooking.setId("B1");

        when(bookingRepo.save(any()))
                .thenReturn(Mono.just(savedBooking));

        // Mocking passenger save operation (usually multiple passengers).
        when(passengerRepo.saveAll(anyList()))
                .thenReturn(Flux.just(new Passenger()));

        // Creating passenger request data.
        PassengerRequest p = new PassengerRequest();
        p.setName("John");
        p.setAge(22);
        p.setGender(Gender.MALE);
        p.setLuggageWeight(10);

        // Complete booking request setup.
        BookingRequest req = new BookingRequest();
        req.setEmail("abc@gmail.com");
        req.setPrimaryPassenger("John");
        req.setSeats(1);
        req.setPassengers(List.of(p));

        // Calling the method under test.
        Booking result = service.book("F123", req).block();

        // Making sure the booking is created.
        assertNotNull(result);

        // Verifying repository calls happened.
        verify(bookingRepo, times(1)).save(any());
        verify(passengerRepo, times(1)).saveAll(anyList());
        verify(flightService, times(1)).getFlightById("F123");
    }

    @Test
    void testGetBooking_notFound() {
        // If no booking exists for the PNR, repository returns empty.
        when(bookingRepo.findByPnr("XX"))
                .thenReturn(Mono.empty());

        Booking result = service.getBooking("XX").block();

        // Since nothing was returned — result must be null.
        assertNull(result);
    }
}
