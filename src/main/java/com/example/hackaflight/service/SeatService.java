package com.example.hackaflight.service;

import com.example.hackaflight.model.core.Flight;
import com.example.hackaflight.model.core.Seat;
import com.example.hackaflight.repository.FlightRepository;
import com.example.hackaflight.repository.SeatRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SeatService {

    Logger log = LoggerFactory.getLogger(SeatService.class);

    @Autowired
    private SeatRepository seatRepository;

    @Autowired
    private FlightRepository flightRepository;

    public Seat saveSeat(
            String seatNumber,
            Long flightId,
            String classType,
            boolean isAvaibale,
            Double price
    ) {
        Flight flight = flightRepository.findById(flightId)
                .orElseThrow(() -> {
                    log.info("Could not find flight for Id : {}", flightId);
                    return new IllegalArgumentException("Could not find flight for Id : " + flightId);
                });

        Seat seat = new Seat(flight, price, isAvaibale, seatNumber, classType);
        return seatRepository.save(seat);
    }
}
