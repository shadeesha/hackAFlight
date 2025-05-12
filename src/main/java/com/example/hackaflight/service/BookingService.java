package com.example.hackaflight.service;

import com.example.hackaflight.configuration.RabbitConfig;
import com.example.hackaflight.dto.BookingDTO;
import com.example.hackaflight.model.core.Booking;
import com.example.hackaflight.model.core.Flight;
import com.example.hackaflight.model.core.Passenger;
import com.example.hackaflight.model.core.Seat;
import com.example.hackaflight.model.support.Baggage;
import com.example.hackaflight.repository.*;
import com.example.hackaflight.repository.specification.BookingSpecification;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

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

    @Autowired
    private RabbitTemplate rabbitTemplate;

    private ConcurrentHashMap<Long, Boolean> seatMap = new ConcurrentHashMap<>();

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

        if (seatMap.putIfAbsent(seatId, true) != null) throw new Exception("Seat is locked");

        log.info("Found passenger : {} and flight : {} and seat {}", passenger.getFirstName(), flight.getName(), seat.getSeatNumber());
        Baggage baggage = new Baggage(baggageType, Double.parseDouble(baggageWeight), passenger);
        baggage = baggageRepository.save(baggage);
        log.info("Saved baggage for the booking");
        try{
            if(seat.isAvailable()) {
                seat.setAvailable(false);
                Booking booking = new Booking(passenger, flight, bookingDate, status, seat, baggage);
                booking = bookingRepository.save(booking);
                BookingDTO bookingDTO = new BookingDTO(
                        passenger.getEmail(),
                        bookingDate,
                        booking.getSeat().getSeatNumber(),
                        booking.getFlight().getName(),
                        passenger.getFirstName()
                );
                rabbitTemplate.convertAndSend(RabbitConfig.QUEUE, bookingDTO);
                return booking;
            } else {
                throw new Exception("Seat is not available");
            }
        } finally {
            seatMap.remove(seatId);
        }
    }

    public List<Booking> getBookings(Long passengerId, Long flightId, String bookingDate) {
        Specification<Booking> specification = Specification
                .where(BookingSpecification.hasPassengerId(passengerId))
                .and(BookingSpecification.hasBookinDate(bookingDate))
                .and(BookingSpecification.hasFlightId(flightId));

        return bookingRepository.findAll(specification);
    }
}
