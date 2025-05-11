package com.example.hackaflight.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class BookingDTO implements Serializable {
    private String passengerEmail;
    private String passengerName;
    private String flightNumber;
    private String seatNumber;
    private String date;

    public BookingDTO() {}

    public BookingDTO(String passengerEmail, String date, String seatNumber, String flightNumber, String passengerName) {
        this.passengerEmail = passengerEmail;
        this.date = date;
        this.seatNumber = seatNumber;
        this.flightNumber = flightNumber;
        this.passengerName = passengerName;
    }
}
