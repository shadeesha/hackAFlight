package com.example.hackaflight.service;

import com.example.hackaflight.model.core.Airport;
import com.example.hackaflight.repository.AirportRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AirportService {

    Logger log = LoggerFactory.getLogger(AirportService.class);

    @Autowired
    private AirportRepository airportRepository;

    @Transactional
    public Airport createAirport(
            String code,
            String name,
            String city,
            String country
    ) {
        Airport airport = new Airport(code, name, city, country);
        log.info("Saving Airport : {}", airport.getName());
        return airportRepository.save(airport);
    }
}
