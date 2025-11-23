package com.flightapp.service;

import com.flightapp.dto.BookingCountPerDayDTO;
import com.flightapp.dto.PassengerCountDTO;
import com.flightapp.dto.SeatsPerFlightDTO;
import com.flightapp.model.Booking;
import com.flightapp.model.Flight;
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
import reactor.test.StepVerifier;

import java.time.LocalDateTime;
import java.util.List;

import static org.mockito.ArgumentMatchers.*;
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

        Flight flight = new Flight();
        flight.setId("F100");

        Booking saved = new Booking();
        saved.setId("B1");
        saved.setPnr("ABCD1234");
        saved.setEmail("test@mail.com");
        saved.setBookingTime(LocalDateTime.now());
        saved.setSeatsBooked(1);
        saved.setFlightId("F100");

        when(flightService.getFlightById("F100"))
                .thenReturn(Mono.just(flight));

        when(bookingRepo.save(any(Booking.class)))
                .thenReturn(Mono.just(saved));

        when(passengerRepo.saveAll(any(Iterable.class)))
                .thenReturn(Flux.empty());

        PassengerRequest pr = new PassengerRequest();
        pr.setName("John");
        pr.setAge(25);
        pr.setGender(Gender.MALE);
        pr.setLuggageWeight(10);

        BookingRequest req = new BookingRequest();
        req.setEmail("test@mail.com");
        req.setPrimaryPassenger("John");
        req.setSeats(1);
        req.setPassengers(List.of(pr));

        StepVerifier.create(service.book("F100", req))
                .expectNextMatches(b -> b.getId().equals("B1"))
                .verifyComplete();

        verify(bookingRepo, times(1)).save(any());
        verify(passengerRepo, times(1)).saveAll(any(Iterable.class));
        verify(flightService, times(1)).getFlightById("F100");
    }
    @Test
    void testGetBooking_NotFound() {

        when(bookingRepo.findByPnr("XXX")).thenReturn(Mono.empty());

        StepVerifier.create(service.getBooking("XXX"))
                .expectComplete()
                .verify();
    }
    @Test
    void testCancelBooking() {

        Booking b = new Booking();
        b.setId("B10");
        b.setPnr("P123");
        b.setStatus("CONFIRMED");

        when(bookingRepo.findByPnr("P123")).thenReturn(Mono.just(b));
        when(bookingRepo.save(any(Booking.class))).thenReturn(Mono.just(b));

        StepVerifier.create(service.cancelBooking("P123"))
                .verifyComplete();

        verify(bookingRepo).save(any(Booking.class));
    }
    @Test
    void testDeleteBooking() {

        Booking b = new Booking();
        b.setId("B11");
        b.setPnr("DEL123");

        when(bookingRepo.findByPnr("DEL123"))
                .thenReturn(Mono.just(b));

        when(bookingRepo.delete(b))
                .thenReturn(Mono.empty());

        StepVerifier.create(service.deleteBooking("DEL123"))
                .verifyComplete();

        verify(bookingRepo).delete(b);
    }
    @Test
    void testGetSeatsBooked() {
        SeatsPerFlightDTO dto = new SeatsPerFlightDTO();
        dto.setFlightId("F1");
        dto.setTotalSeatsBooked(50);

        when(bookingRepo.getSeatsBookedGroupedByFlight("CONFIRMED"))
                .thenReturn(Flux.just(dto));

        StepVerifier.create(service.getSeatsBooked("CONFIRMED"))
                .expectNext(dto)
                .verifyComplete();
    }
    @Test
    void testGetDailyBookingCount() {
        BookingCountPerDayDTO dto = new BookingCountPerDayDTO("2025-01-01", 20);

        when(bookingRepo.getDailyBookingCount())
                .thenReturn(Flux.just(dto));

        StepVerifier.create(service.getDailyBookingCount())
                .expectNext(dto)
                .verifyComplete();
    }
    @Test
    void testGetPassengerCountPerFlight() {

        PassengerCountDTO dto = new PassengerCountDTO("F1", 120);

        when(passengerRepo.getPassengerCountPerFlight())
                .thenReturn(Flux.just(dto));

        StepVerifier.create(service.getPassengerCountPerFlight())
                .expectNext(dto)
                .verifyComplete();
    }
}
