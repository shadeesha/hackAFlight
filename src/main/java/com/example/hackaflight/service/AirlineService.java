package com.example.hackaflight.service;

import com.example.hackaflight.model.core.Airline;
import com.example.hackaflight.repository.AirlineRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AirlineService {

    Logger log = LoggerFactory.getLogger(AirlineService.class);

    @Autowired
    private AirlineRepository airlineRepository;

    @Transactional
    public Airline createAirline(String name, String code, String country) {
        Airline airline = new Airline(country, code, name);
        log.info("Saving Ariline {}", name);
        return airlineRepository.save(airline);
    }
}
