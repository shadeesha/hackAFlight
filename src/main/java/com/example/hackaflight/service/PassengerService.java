package com.example.hackaflight.service;

import com.example.hackaflight.model.core.Passenger;
import com.example.hackaflight.repository.PassengerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PassengerService {

    Logger log = LoggerFactory.getLogger(PassengerService.class);

    @Autowired
    private PassengerRepository passengerRepository;

    public Passenger createPassenger(
        String firstName,
        String lastName,
        String email,
        String phoneNumber,
        String passportNumber
    ) {
        Passenger passenger = new Passenger(
                firstName,
                lastName,
                email,
                phoneNumber,
                passportNumber
        );

        log.info("Saving Passenger : {} {}", firstName, lastName);
        return passengerRepository.save(passenger);
    }
}
