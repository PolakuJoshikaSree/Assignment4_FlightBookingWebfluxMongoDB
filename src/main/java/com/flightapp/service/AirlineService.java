package com.flightapp.service;

import com.flightapp.model.Airline;
import com.flightapp.repository.AirlineRepository;
import com.flightapp.request.AddAirlineRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class AirlineService {

    // Repository to access airline collection in MongoDB.
    private final AirlineRepository repo;

    // Creates a new airline based on request details.
    public Mono<Airline> addAirline(AddAirlineRequest req) {
        Airline a = new Airline();
        a.setAirlineName(req.getAirlineName());
        a.setAirlineCode(req.getAirlineCode());
        a.setCountry(req.getCountry());
        return repo.save(a);
    }

    // Fetches all airlines stored in the system.
    public Flux<Airline> getAll() {
        return repo.findAll();
    }

    // Looks up airline using its unique airline code.
    public Mono<Airline> getAirlineByCode(String code) {
        return repo.findByAirlineCode(code);
    }

    // Updates an existing airline using its code.
    public Mono<Airline> updateAirline(String code, AddAirlineRequest req) {
        return repo.findByAirlineCode(code)
                .flatMap(existing -> {
                    existing.setAirlineName(req.getAirlineName());
                    existing.setCountry(req.getCountry());
                    return repo.save(existing);
                });
    }

    // Deletes an airline using its code.
    public Mono<Void> deleteAirline(String code) {
        return repo.findByAirlineCode(code)
                .flatMap(repo::delete);
    }

    // Deletes airline directly using MongoDB ID.
    public Mono<Void> deleteAirlineById(String id) {
        return repo.deleteById(id);
    }
}
