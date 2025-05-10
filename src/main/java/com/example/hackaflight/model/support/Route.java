package com.example.hackaflight.model.support;

import com.example.hackaflight.model.core.Airport;
import com.example.hackaflight.model.core.Flight;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Setter
@Getter
@Table(name = "troute")
public class Route {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "origin_airport_id")
    private Airport origin;

    @ManyToOne
    @JoinColumn(name = "destination_airport_id")
    private Airport destination;

    @Column
    private String routeCode;

    @Column
    private String description;

    @OneToMany(mappedBy = "route")
    @Column(nullable = true)
    private List<Flight> flights = new ArrayList<>();

    public Route(){}

    public Route(Airport origin, Airport destination, String routeCode, String description, List<Flight> flights) {
        this.origin = origin;
        this.destination = destination;
        this.routeCode = routeCode;
        this.description = description;
        this.flights = flights;
    }

    public Route(String description, String routeCode, Airport destination, Airport origin, Long id, List<Flight> flights) {
        this.description = description;
        this.routeCode = routeCode;
        this.destination = destination;
        this.origin = origin;
        this.id = id;
        this.flights = flights;
    }

    @Override
    public String toString() {
        return "Route{" +
                "id=" + id +
                ", origin=" + origin +
                ", destination=" + destination +
                ", routeCode='" + routeCode + '\'' +
                ", description='" + description + '\'' +
                ", flights=" + flights +
                '}';
    }
}
