package com.example.hackaflight.model.core;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "tbooking")
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "passenger_id")
    private Passenger passenger;

    @ManyToOne
    @JoinColumn(name = "flight_id")
    private Flight flight;

    @Column
    private String bookingDate;

    @Column
    private String status;

    @Column
    private String seatNumber;

    public Booking(){}

    public Booking(Passenger passenger, Flight flight, String bookingDate, String status, String seatNumber) {
        this.passenger = passenger;
        this.flight = flight;
        this.bookingDate = bookingDate;
        this.status = status;
        this.seatNumber = seatNumber;
    }

    public Booking(Long id, String seatNumber, String status, String bookingDate, Flight flight, Passenger passenger) {
        this.id = id;
        this.seatNumber = seatNumber;
        this.status = status;
        this.bookingDate = bookingDate;
        this.flight = flight;
        this.passenger = passenger;
    }

    @Override
    public String toString() {
        return "Booking{" +
                "id=" + id +
                ", passenger=" + passenger +
                ", flight=" + flight +
                ", bookingDate='" + bookingDate + '\'' +
                ", status='" + status + '\'' +
                ", seatNumber='" + seatNumber + '\'' +
                '}';
    }
}
