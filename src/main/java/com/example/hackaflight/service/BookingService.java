package com.example.hackaflight.service;

import com.example.hackaflight.model.core.Booking;
import com.example.hackaflight.model.core.Flight;
import com.example.hackaflight.model.core.Passenger;
import com.example.hackaflight.model.core.Seat;
import com.example.hackaflight.model.support.Baggage;
import com.example.hackaflight.repository.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookingService {

    Logger log = LoggerFactory.getLogger(BookingService.class);

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private PassengerRepository passengerRepository;

    @Autowired
    private FlightRepository flightRepository;

    @Autowired
    private SeatRepository seatRepository;

    @Autowired
    private BaggageRepository baggageRepository;

    public Booking addBooking(
            Long passengerId,
            Long flightId,
            String bookingDate,
            String status,
            Long seatId,
            String baggageWeight,
            String baggageType
    ) throws Exception {
        Passenger passenger = passengerRepository.findById(passengerId)
                .orElseThrow(() -> {
                    log.info("Could not find Passenger for Id : {}", passengerId);
                    return new IllegalArgumentException("Could not find Passenger for Id : " + passengerId);
                });
        Flight flight = flightRepository.findById(flightId)
                .orElseThrow(() -> {
                    log.info("Could not find Flight for Id : {}", flightId);
                    return new IllegalArgumentException("Could not find Flight for Id : " + flightId);
                });
        Seat seat = seatRepository.findById(seatId)
                .orElseThrow(() -> {
                    log.info("Could not find Seat for Id : {}", seatId);
                    return new IllegalArgumentException("Could not find Seat for Id : " + seatId);
                });
        log.info("Found passenger : {} and flight : {} and seat {}", passenger.getFirstName(), flight.getName(), seat.getSeatNumber());
        Baggage baggage = new Baggage(baggageType, Double.parseDouble(baggageWeight), passenger);
        baggage = baggageRepository.save(baggage);
        log.info("Saved baggage for the booking");
        if(seat.isAvailable()) {
            seat.setAvailable(false);
            Booking booking = new Booking(passenger, flight, bookingDate, status, seat, baggage);
            return bookingRepository.save(booking);
        } else {
            throw new Exception("Seat is not available");
        }
    }
}
