package com.example.hackaflight.controller;

import com.example.hackaflight.model.core.Airline;
import com.example.hackaflight.model.core.Airport;
import com.example.hackaflight.model.core.Flight;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

@Controller
public class FlightController {

    @QueryMapping
    public Flight getFlightByName(
            @Argument("name") String name,
            @Argument("price") Double price,
            @Argument("status") String status,
            @Argument("airline") String airlineName) {
        Airline airLine = new Airline(airlineName, "MK40", "England");
        Airport originAirport = new Airport("England", "London", "Hethrow", "600");
        Airport destinationAirport = new Airport("Sri Lanka", "Colombo", "BNK", "600");
        return new Flight(airLine, "TODAY", "TOMORROW", originAirport, destinationAirport, name);
    }
}
