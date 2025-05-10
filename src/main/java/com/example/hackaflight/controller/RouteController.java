package com.example.hackaflight.controller;

import com.example.hackaflight.model.support.Route;
import com.example.hackaflight.service.RouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class RouteController {

    @Autowired
    private RouteService routeService;

    @MutationMapping
    public Route createRoute(
            @Argument("originAirportId") Long origingAirportId,
            @Argument("destinationAirportId") Long destinationAirportId,
            @Argument("routeCode") String routeCode,
            @Argument("description") String description,
            @Argument("flights") List<String> flights
    ) throws Exception {
        if(flights != null){
            List<Long> flightIds = flights.stream()
                    .map(Long::parseLong)
                    .collect(Collectors.toList());
            return routeService.createRoute(origingAirportId, destinationAirportId, routeCode, description, flightIds);
        } else {
            Route returnRoute = routeService.createRoute(origingAirportId, destinationAirportId, routeCode, description, null);
            return returnRoute;
        }

    }
}
