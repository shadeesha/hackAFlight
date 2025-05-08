package com.example.hackaflight.controller;

import com.example.hackaflight.model.core.Booking;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

@Controller
public class BookingController {

    @QueryMapping
    public Booking createBooking(
            @Argument Long passengerId,
            @Argument Long flightId,
            @Argument String bookingDate,
            @Argument String status
    ) {

        return null;
    }
}
