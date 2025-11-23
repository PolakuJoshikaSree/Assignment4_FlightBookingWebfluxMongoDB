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

    private final AirlineRepository repo;

    public Mono<Airline> addAirline(AddAirlineRequest req) {
        Airline a = new Airline();
        a.setAirlineName(req.getAirlineName());
        a.setAirlineCode(req.getAirlineCode());
        a.setCountry(req.getCountry());
        return repo.save(a);
    }

    public Flux<Airline> getAll() {
        return repo.findAll();
    }

    public Mono<Airline> getAirlineByCode(String code) {
        return repo.findByAirlineCode(code);
    }

    public Mono<Airline> updateAirline(String code, AddAirlineRequest req) {
        return repo.findByAirlineCode(code)
                .flatMap(existing -> {
                    existing.setAirlineName(req.getAirlineName());
                    existing.setCountry(req.getCountry());
                    return repo.save(existing);
                });
    }

    public Mono<Void> deleteAirline(String code) {
        return repo.findByAirlineCode(code)
                .flatMap(repo::delete);
    }

    public Mono<Void> deleteAirlineById(String id) {  
        return repo.deleteById(id);
    }
}
