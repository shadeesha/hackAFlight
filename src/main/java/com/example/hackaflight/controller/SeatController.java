package com.example.hackaflight.controller;

import com.example.hackaflight.model.core.Seat;
import com.example.hackaflight.service.SeatService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.stereotype.Controller;

@Controller
public class SeatController {

    Logger log = LoggerFactory.getLogger(SeatController.class);

    @Autowired
    private SeatService seatService;

    @MutationMapping
    public Seat createSeat(
            @Argument("seatNumber") String seatNumber,
            @Argument("flightId") Long flightId,
            @Argument("classType") String classType,
            @Argument("isAvailable") String isAvailable,
            @Argument("price") String price
    ) throws Exception{
        try{
            return seatService.saveSeat(seatNumber, flightId, classType,Boolean.parseBoolean(isAvailable), Double.parseDouble(price));
        } catch (Exception e) {
            log.info("Could not save the seat {}", e.getMessage());
            throw new Exception("Could not save the seat");
        }
    }
}
