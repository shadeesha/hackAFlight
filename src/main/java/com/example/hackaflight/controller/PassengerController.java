package com.example.hackaflight.controller;

import com.example.hackaflight.model.core.Passenger;
import com.example.hackaflight.service.PassengerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.stereotype.Controller;

@Controller
public class PassengerController {

    Logger log = LoggerFactory.getLogger(PassengerController.class);

    @Autowired
    private PassengerService passengerService;

    @MutationMapping
    public Passenger createPassenger(
            @Argument("firstName") String firstName,
            @Argument("lastName") String lastName,
            @Argument("email") String email,
            @Argument("phoneNumber") String phoneNumber,
            @Argument("passportNumber") String passportNumber
    ) throws Exception {
        Passenger passenger = passengerService.createPassenger(firstName, lastName, email, phoneNumber, passportNumber);
        if(passenger != null) {
            return passenger;
        } else {
            throw new Exception("Could not create the Passenger : " + firstName + " " + lastName);
        }
    }
}
