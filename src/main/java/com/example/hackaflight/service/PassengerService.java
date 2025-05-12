package com.example.hackaflight.service;

import com.example.hackaflight.model.core.Booking;
import com.example.hackaflight.model.core.Flight;
import com.example.hackaflight.model.core.Passenger;
import com.example.hackaflight.repository.BookingRepository;
import com.example.hackaflight.repository.PassengerRepository;
import com.example.hackaflight.repository.specification.BookingSpecification;
import com.example.hackaflight.repository.specification.PassengerSpecification;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PassengerService {

    Logger log = LoggerFactory.getLogger(PassengerService.class);

    @Autowired
    private PassengerRepository passengerRepository;

    @Autowired
    private BookingRepository bookingRepository;

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

    public List<Passenger> getPassengers(String firstName, String email, String passportNumber) {
        Specification<Passenger> specification = Specification
                .where(PassengerSpecification.hasFirstName(firstName))
                .and(PassengerSpecification.hasEmail(email))
                .and(PassengerSpecification.hasPassportNumber(passportNumber));

        List<Passenger> passengers = passengerRepository.findAll(specification);
        passengers
                .forEach(passenger -> {
                    Long id = passenger.getId();
                    Specification<Booking> bookingSpecification = Specification
                            .where(BookingSpecification.hasPassengerId(id));
                    List<Booking> bookings = bookingRepository.findAll(bookingSpecification);
                    passenger.setBookings(bookings);
                });
        return passengers;
    }
}
