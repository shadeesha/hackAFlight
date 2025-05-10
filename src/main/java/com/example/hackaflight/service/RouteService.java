package com.example.hackaflight.service;

import com.example.hackaflight.model.core.Airport;
import com.example.hackaflight.model.core.Flight;
import com.example.hackaflight.model.support.Route;
import com.example.hackaflight.repository.AirportRepository;
import com.example.hackaflight.repository.FlightRepository;
import com.example.hackaflight.repository.RouteRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class RouteService {

    Logger log = LoggerFactory.getLogger(RouteService.class);

    @Autowired
    private RouteRepository routeRepository;

    @Autowired
    private FlightRepository flightRepository;

    @Autowired
    private AirportRepository airportRepository;

    @Transactional
    public Route createRoute (
            Long originAirportId,
            Long destinationAirportId,
            String routeCode,
            String description,
            List<Long> flightIds
    ) {

        Airport originAirport = airportRepository.findById(originAirportId)
                .orElseThrow(() -> {
                    log.info("Original Airport Not found for Id : {}" ,originAirportId);
                    return new IllegalArgumentException("Original Airport Not found for Id : " + originAirportId);
                });
        Airport destiantionAirport = airportRepository.findById(destinationAirportId)
                .orElseThrow(() -> {
                    log.info("Destination Airport not found for Id : {}" ,destinationAirportId);
                    return new IllegalArgumentException("Destination Airport not found for Id : " + destinationAirportId);
                });

        List<Flight> flights = (flightIds != null) ?
                flightIds.stream()
                        .map(id -> flightRepository.findById(id)
                                .orElseThrow(() -> {
                                    log.info("Cannot find flight for Id : {}" ,id);
                                    return new IllegalArgumentException("Cannot find flight for Id : " + id);
                                }))
                        .toList() : new ArrayList<>();

        Route route = new Route(originAirport, destiantionAirport, routeCode, description, flights);
        log.info("Saving route : {}", routeCode);
        return routeRepository.save(route);
    }
}
