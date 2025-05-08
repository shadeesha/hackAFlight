package com.example.hackaflight.service;

import com.example.hackaflight.model.core.Airline;
import com.example.hackaflight.model.core.Airport;
import com.example.hackaflight.model.core.Flight;
import com.example.hackaflight.repository.AirlineRepository;
import com.example.hackaflight.repository.AirportRepository;
import com.example.hackaflight.repository.FlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.Optional;

@Service
public class FlightService {

    @Autowired
    private FlightRepository flightRepository;

    @Autowired
    private AirportRepository airportRepository;

    @Autowired
    private AirlineRepository airlineRepository;

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
            Airline airline = airlineOptional.get();

            Flight flight  = new Flight(name, originAirport, destinationAirport, departureTime, arrivalTime, airline);
            return flightRepository.save(flight);
        } else {
            throw new Exception("Could not find Airports for the flight");
        }
    }
}
