package com.example.hackaflight.controller;

import com.example.hackaflight.model.Flight;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

@Controller
public class FlightController {

    @QueryMapping
    public Flight getFlightByName(@Argument("name") String name) {
        return new Flight(name, 240, "WHITE");
    }
}
