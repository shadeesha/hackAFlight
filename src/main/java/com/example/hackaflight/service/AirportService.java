package com.example.hackaflight.service;

import com.example.hackaflight.model.core.Airport;
import com.example.hackaflight.repository.AirportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AirportService {

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
        return airportRepository.save(airport);
    }
}
