package com.example.hackaflight.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "tFlight")
public class Flight {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "seats")
    private int seats;

    @Column(name = "color")
    private String color;

    public Flight(String name, int seats, String color) {
        this.name = name;
        this.seats = seats;
        this.color = color;
    }
}
