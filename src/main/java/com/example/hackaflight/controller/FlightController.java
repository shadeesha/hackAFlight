package com.example.hackaflight.controller;

import com.example.hackaflight.model.core.Flight;
import com.example.hackaflight.service.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

@Controller
public class FlightController {

    @Autowired
    private FlightService flightService;

    @QueryMapping
    public Flight getFlightByName(
            @Argument("name") String name
    ) throws Exception {
        return flightService.findFlightByName(name);
    }

    @MutationMapping
    public Flight createFlight(
            @Argument("name") String name,
            @Argument("originAirport") Long originAirportId,
            @Argument("destinationAirport") Long destinationAirportId,
            @Argument("departureTime") String departureTime,
            @Argument("arrivalTime") String arrivalTime,
            @Argument("airline") Long airline
    ) throws Exception {
        Flight flight = flightService.createFlight(
                name,
                originAirportId,
                destinationAirportId,
                departureTime,
                arrivalTime,
                airline);

        if(flight != null) {
            return flight;
        } else {
            throw new Exception("Could not save the flight");
        }
    }
}
