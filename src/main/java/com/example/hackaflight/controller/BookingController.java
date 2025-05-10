package com.example.hackaflight.controller;

import com.example.hackaflight.model.core.Booking;
import com.example.hackaflight.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
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
            @Argument("seatId") Long seatId,
            @Argument("baggageWeight") String baggageWeight,
            @Argument("baggageType") String baggageType
    ) throws Exception {
        try {

            return bookingService.addBooking(
                    passengerId,
                    flightId,
                    bookingDate,
                    status,
                    seatId,
                    baggageWeight,
                    baggageType
            );

        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
}
