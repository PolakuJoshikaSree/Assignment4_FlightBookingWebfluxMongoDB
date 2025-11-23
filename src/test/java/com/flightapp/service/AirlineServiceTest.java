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
        AddAirlineRequest req = new AddAirlineRequest();
        req.setAirlineName("Air India");

        Airline saved = new Airline();
        saved.setAirlineName("Air India");

        when(repo.save(any(Airline.class))).thenReturn(Mono.just(saved));

        Airline result = service.addAirline(req).block();

        assertEquals("Air India", result.getAirlineName());
    }

    @Test
    void testGetAirlineByCode() {
        Airline airline = new Airline();
        airline.setAirlineCode("AI101");

        when(repo.findByAirlineCode("AI101"))
                .thenReturn(Mono.just(airline));

        Airline result = service.getAirlineByCode("AI101").block();

        assertEquals("AI101", result.getAirlineCode());
    }
}
