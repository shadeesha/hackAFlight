package com.example.hackaflight.controller;

import com.example.hackaflight.model.core.Airport;
import com.example.hackaflight.service.AirportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class AirportController {

    @Autowired
    private AirportService airportService;

    @MutationMapping
    public Airport createAirport(
            @Argument("code") String code,
            @Argument("name") String name,
            @Argument("city") String city,
            @Argument("country") String country
    ) {
        Airport airport = airportService.createAirport(code, name, city, country);
        if(airport != null) {
            return airport;
        } else {
            throw new NullPointerException("Could not save the Airport");
        }
    }

    @QueryMapping
    public List<Airport> getAirports(
            @Argument("code") String code,
            @Argument("name") String name,
            @Argument("city") String city,
            @Argument("country") String country
    ) {
        return airportService.getAirports(code, name, city, country);
    }

}
