package com.example.hackaflight.model.core;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "tflight")
public class Flight {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @ManyToOne
    @JoinColumn(name = "origin_airport_id")
    private Airport originAirport;

    @ManyToOne
    @JoinColumn(name = "destination_airport_id")
    private Airport destinationAirport;

    @Column
    private String departureTime;

    @Column
    private String arrivalTime;

    @ManyToOne
    @JoinColumn(name = "airline_id")
    private Airline airline;

    public Flight(){

    }

    public Flight(Long id, Airline airline, String arrivalTime, String departureTime, Airport originAirport, Airport destinationAirport, String name) {
        this.id = id;
        this.airline = airline;
        this.arrivalTime = arrivalTime;
        this.departureTime = departureTime;
        this.originAirport = originAirport;
        this.destinationAirport = destinationAirport;
        this.name = name;
    }

    public Flight(String name, Airport originAirport, Airport destinationAirport, String departureTime, String arrivalTime, Airline airline) {
        this.name = name;
        this.originAirport = originAirport;
        this.destinationAirport = destinationAirport;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
        this.airline = airline;
    }

    @Override
    public String toString() {
        return "Flight{" +
                "id=" + id +
                ", airline=" + airline +
                ", arrivalTime='" + arrivalTime + '\'' +
                ", departureTime='" + departureTime + '\'' +
                ", destinationAirport=" + destinationAirport +
                ", originAirport=" + originAirport +
                ", name='" + name + '\'' +
                '}';
    }
}
