package com.example.hackaflight.model.core;

import com.example.hackaflight.model.support.Route;
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
    @JoinColumn(name = "route_id")
    private Route route;

    @Column
    private String departureTime;

    @Column
    private String arrivalTime;

    @ManyToOne
    @JoinColumn(name = "airline_id")
    private Airline airline;

    public Flight(){
    }

    public Flight(Long id, Airline airline, String arrivalTime, String departureTime, String name, Route route) {
        this.id = id;
        this.airline = airline;
        this.arrivalTime = arrivalTime;
        this.departureTime = departureTime;
        this.name = name;
        this.route = route;
    }

    public Flight(String name, String departureTime, String arrivalTime, Airline airline, Route route) {
        this.name = name;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
        this.airline = airline;
        this.route = route;
    }

    @Override
    public String toString() {
        return "Flight{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", route=" + route +
                ", departureTime='" + departureTime + '\'' +
                ", arrivalTime='" + arrivalTime + '\'' +
                ", airline=" + airline +
                '}';
    }
}
