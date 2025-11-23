package com.flightapp.service;

import com.flightapp.model.Airline;
import com.flightapp.repository.AirlineRepository;
import com.flightapp.request.AddAirlineRequest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Mono;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AirlineServiceTest {

    @Mock
    private AirlineRepository repo;

    @InjectMocks
    private AirlineService service;

    @Test
    void testAddAirline() {
        // Preparing a sample request object the same way the controller would send it.
        AddAirlineRequest req = new AddAirlineRequest();
        req.setAirlineName("Air India");

        // Mock airline saved object returned by MongoDB.
        Airline saved = new Airline();
        saved.setAirlineName("Air India");

        // Telling Mockito what the repository should return when saving.
        when(repo.save(any(Airline.class))).thenReturn(Mono.just(saved));

        // Calling the service method.
        Airline result = service.addAirline(req).block();

        // Checking whether the airline got saved correctly.
        assertEquals("Air India", result.getAirlineName());
    }

    @Test
    void testGetAirlineByCode() {
        // Creating a mock airline to return for the given code.
        Airline airline = new Airline();
        airline.setAirlineCode("AI101");

        // Mock the repository response.
        when(repo.findByAirlineCode("AI101"))
                .thenReturn(Mono.just(airline));

        // Calling the method.
        Airline result = service.getAirlineByCode("AI101").block();

        // Making sure the correct airline is returned.
        assertEquals("AI101", result.getAirlineCode());
    }
}
