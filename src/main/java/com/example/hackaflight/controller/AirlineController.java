package com.example.hackaflight.controller;

import com.example.hackaflight.model.core.Airline;
import com.example.hackaflight.service.AirlineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class AirlineController {

    @Autowired
    private AirlineService airlineService;

    @MutationMapping
    public Airline createAirLine(
        @Argument("name") String name,
        @Argument("code") String code,
        @Argument("country") String country
    ) throws NullPointerException{
        Airline airline = airlineService.createAirline(name, code, country);
        if (airline != null) {
            return airline;
        } else {
            throw new NullPointerException("Could not save the Airline");
        }
    }

    @QueryMapping
    public List<Airline> getAirLines(
        @Argument("name") String name,
        @Argument("code") String code,
        @Argument("country") String country
    ) {
        return airlineService.getAirLines(name, code, country);
    }
}
