package com.example.hackaflight.service;

import com.example.hackaflight.model.core.Airline;
import com.example.hackaflight.model.core.Flight;
import com.example.hackaflight.model.support.Route;
import com.example.hackaflight.repository.AirlineRepository;
import com.example.hackaflight.repository.AirportRepository;
import com.example.hackaflight.repository.FlightRepository;
import com.example.hackaflight.repository.RouteRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class FlightService {

    Logger log = LoggerFactory.getLogger(FlightService.class);

    @Autowired
    private FlightRepository flightRepository;

    @Autowired
    private AirportRepository airportRepository;

    @Autowired
    private AirlineRepository airlineRepository;

    @Autowired
    private RouteRepository routeRepository;

    @Transactional
    public Flight createFlight(
        String name,
        String departureTime,
        String arrivalTime,
        Long airlineId,
        Long routeId
    ) {
        Airline airline = airlineRepository.findById(airlineId)
                .orElseThrow(() -> {
                    log.info("Could not find airline for Id : {}", airlineId);
                    return new IllegalArgumentException("Cannnot find airline for Id : " + airlineId);
                });
        Route route = routeRepository.findById(routeId)
                .orElseThrow(() -> {
                    log.info("Could not find route for Id : {}", routeId);
                    return new IllegalArgumentException("Cannot find route for Id : " + routeId);
                });

        Flight flight = new Flight(name, departureTime, arrivalTime, airline, route);
        log.info("Saving flight : {}", name);
        return flightRepository.save(flight);
    }

    @Transactional(readOnly = true)
    public Flight findFlightByName(String name) throws Exception {
        Optional<Flight> flightOptional = flightRepository.findByName(name);
        if(flightOptional.isPresent()) {
            log.info("Found flight : {}", name);
            return flightOptional.get();
        } else {
            log.info("Could not find any flight for {}", name);
            throw new Exception("Could not find the flight");
        }
    }
}
