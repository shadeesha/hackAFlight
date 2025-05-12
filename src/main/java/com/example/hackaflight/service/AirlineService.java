package com.example.hackaflight.service;

import com.example.hackaflight.model.core.Airline;
import com.example.hackaflight.model.core.Flight;
import com.example.hackaflight.repository.AirlineRepository;
import com.example.hackaflight.repository.FlightRepository;
import com.example.hackaflight.repository.specification.AirLineSpecification;
import com.example.hackaflight.repository.specification.FlightSpecification;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AirlineService {

    Logger log = LoggerFactory.getLogger(AirlineService.class);

    @Autowired
    private AirlineRepository airlineRepository;

    @Autowired
    private FlightRepository flightRepository;

    @Transactional
    public Airline createAirline(String name, String code, String country) {
        Airline airline = new Airline(country, code, name);
        log.info("Saving Ariline {}", name);
        return airlineRepository.save(airline);
    }

    public List<Airline> getAirLines(String name, String code, String country) {
        Specification<Airline> specification = Specification
                .where(AirLineSpecification.hasName(name))
                .and(AirLineSpecification.hasCode(code))
                .and(AirLineSpecification.hasCountry(country));

        List<Airline> airlines = airlineRepository.findAll(specification);

        airlines.forEach(airline -> {
            Long airLineId = airline.getId();
            Specification<Flight> flightSpecification = Specification
                    .where(FlightSpecification.hasAirLineId(airLineId));
            List<Flight> flights = flightRepository.findAll(flightSpecification);
            airline.setFlights(flights);
        });

        return airlines;
    }
}
