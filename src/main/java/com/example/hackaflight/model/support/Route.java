package com.example.hackaflight.model.support;

import com.example.hackaflight.model.core.Airport;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

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

    private String routeCode;
    private String description;
}
