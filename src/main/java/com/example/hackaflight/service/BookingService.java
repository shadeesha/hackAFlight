package com.example.hackaflight.service;

import com.example.hackaflight.model.core.Booking;
import com.example.hackaflight.model.core.Flight;
import com.example.hackaflight.model.core.Passenger;
import com.example.hackaflight.repository.BookingRepository;
import com.example.hackaflight.repository.FlightRepository;
import com.example.hackaflight.repository.PassengerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BookingService {

    Logger log = LoggerFactory.getLogger(BookingService.class);

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private PassengerRepository passengerRepository;

    @Autowired
    private FlightRepository flightRepository;

    public Booking addBooking(
            Long passengerId,
            Long flightId,
            String bookingDate,
            String status,
            String seatNumber
    ) throws Exception {
        Optional<Passenger> passengerOptional = passengerRepository.findById(passengerId);
        Optional<Flight> flightOptional = flightRepository.findById(flightId);

        if(passengerOptional.isPresent() && flightOptional.isPresent()) {
            Passenger passenger = passengerOptional.get();
            Flight flight = flightOptional.get();
            log.info("Found passenger : {} and flight : {}", passenger.getFirstName(), flight.getName());
            Booking booking = new Booking(passenger, flight, bookingDate, status, seatNumber);
            return bookingRepository.save(booking);
        } else {
            throw new Exception("Could not find passenger or flight details");
        }
    }
}
