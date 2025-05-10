package com.example.hackaflight.model.core;

import com.example.hackaflight.model.support.Baggage;
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

    @OneToOne
    @JoinColumn(name = "seat_id")
    private Seat seat;

    @OneToOne
    @JoinColumn(name = "baggage_id")
    private Baggage baggage;

    public Booking(){}

    public Booking(Passenger passenger, Flight flight, String bookingDate, String status, Seat seatNumber, Baggage baggage) {
        this.passenger = passenger;
        this.flight = flight;
        this.bookingDate = bookingDate;
        this.status = status;
        this.seat = seatNumber;
        this.baggage = baggage;
    }

    public Booking(Long id, Seat seat, String status, String bookingDate, Flight flight, Passenger passenger, Baggage baggage) {
        this.id = id;
        this.seat = seat;
        this.status = status;
        this.bookingDate = bookingDate;
        this.flight = flight;
        this.passenger = passenger;
        this.baggage = baggage;
    }

    @Override
    public String toString() {
        return "Booking{" +
                "id=" + id +
                ", passenger=" + passenger +
                ", flight=" + flight +
                ", bookingDate='" + bookingDate + '\'' +
                ", status='" + status + '\'' +
                ", seatNumber='" + seat.getSeatNumber() + '\'' +
                '}';
    }
}
