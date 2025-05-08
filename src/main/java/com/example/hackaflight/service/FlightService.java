package com.example.hackaflight.service;

import com.example.hackaflight.model.core.Airline;
import com.example.hackaflight.model.core.Airport;
import com.example.hackaflight.model.core.Flight;
import com.example.hackaflight.repository.AirlineRepository;
import com.example.hackaflight.repository.AirportRepository;
import com.example.hackaflight.repository.FlightRepository;
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

    @Transactional
    public Flight createFlight(
        String name,
        Long originAirportId,
        Long destinationAirportId,
        String departureTime,
        String arrivalTime,
        Long airlineId
    ) throws Exception {
        Optional<Airport> originAirportOptional = airportRepository.findById(originAirportId);
        Optional<Airport> destinationAirportOptional = airportRepository.findById(destinationAirportId);
        Optional<Airline> airlineOptional = airlineRepository.findById(airlineId);
        if(originAirportOptional.isPresent()
                && destinationAirportOptional.isPresent()
                && airlineOptional.isPresent()) {
            Airport originAirport = originAirportOptional.get();
            Airport destinationAirport = destinationAirportOptional.get();
            log.info("Found origin and destination airports. Origin :{}, Destination : {}", originAirport.getName(), destinationAirport.getName());
            Airline airline = airlineOptional.get();
            Flight flight  = new Flight(name, originAirport, destinationAirport, departureTime, arrivalTime, airline);
            log.info("Saving flight information : {}", name);
            return flightRepository.save(flight);
        } else {
            log.info("Could not save flight information : {}", name);
            throw new Exception("Could not find Airports for the flight");
        }
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
