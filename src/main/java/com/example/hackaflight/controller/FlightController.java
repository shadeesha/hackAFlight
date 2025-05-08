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
            @Argument("name") String name) {
        Airline airLine = new Airline(1L, "Sri Lankan AirLine", "MK40", "England");
        Airport originAirport = new Airport(1L,"England", "London", "Hethrow", "600");
        Airport destinationAirport = new Airport(1L, "Sri Lanka", "Colombo", "BNK", "600");
        return new Flight(1L, airLine, "TODAY", "TOMORROW", originAirport, destinationAirport, name);
    }
}
