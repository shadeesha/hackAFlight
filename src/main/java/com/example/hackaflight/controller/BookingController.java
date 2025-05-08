package com.example.hackaflight.controller;

import com.example.hackaflight.model.core.Booking;
import com.example.hackaflight.service.BookingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

@Controller
public class BookingController {

    @Autowired
    private BookingService bookingService;

    @MutationMapping
    public Booking addBooking(
            @Argument("passengerId") Long passengerId,
            @Argument("flightId") Long flightId,
            @Argument("bookingDate") String bookingDate,
            @Argument("status") String status,
            @Argument("seatNumber") String seatNumber
    ) throws Exception {
        Booking booking = bookingService.addBooking(
                passengerId,
                flightId,
                bookingDate,
                status,
                seatNumber
        );

        if(booking != null) {
            return booking;
        } else {
            throw new Exception("Could not save the new booking");
        }

    }
}
