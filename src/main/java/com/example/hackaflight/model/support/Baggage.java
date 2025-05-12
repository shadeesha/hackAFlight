package com.example.hackaflight.model.support;

import com.example.hackaflight.model.core.Passenger;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
@Table(name = "tbaggage")
public class Baggage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "passenger_id")
    private Passenger passenger;

    @Column
    private double weight;

    @Column
    private String type;

    public Baggage(){}

    public Baggage(String type, double weight, Passenger passenger) {
        this.type = type;
        this.weight = weight;
        this.passenger = passenger;
    }

    public Baggage(Long id, Passenger passenger, double weight, String type) {
        this.id = id;
        this.passenger = passenger;
        this.weight = weight;
        this.type = type;
    }
}
