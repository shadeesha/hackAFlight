package com.example.hackaflight.model.core;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "tseat")
public class Seat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "flight_id")
    private Flight flight;

    @Column
    private String seatNumber;

    @Column
    private String classType;

    @Column
    private boolean isAvailable;

    @Column
    private double price;

    public Seat(Long id, double price, String classType, boolean isAvailable, String seatNumber, Flight flight) {
        this.id = id;
        this.price = price;
        this.classType = classType;
        this.isAvailable = isAvailable;
        this.seatNumber = seatNumber;
        this.flight = flight;
    }

    @Override
    public String toString() {
        return "Seat{" +
                "id=" + id +
                ", flight=" + flight +
                ", seatNumber='" + seatNumber + '\'' +
                ", classType='" + classType + '\'' +
                ", isAvailable=" + isAvailable +
                ", price=" + price +
                '}';
    }
}
