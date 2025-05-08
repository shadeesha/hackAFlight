package com.example.hackaflight.model.core;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "tairport")
public class Airport {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String code;

    @Column
    private String name;

    @Column
    private String city;

    @Column
    private String country;

    public Airport(){

    }

    public Airport(String code, String name, String city, String country) {
        this.code = code;
        this.name = name;
        this.city = city;
        this.country = country;
    }

    public Airport(Long id, String country, String city, String name, String code) {
        this.id = id;
        this.country = country;
        this.city = city;
        this.name = name;
        this.code = code;
    }

    @Override
    public String toString() {
        return "Airport{" +
                "id=" + id +
                ", code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", city='" + city + '\'' +
                ", country='" + country + '\'' +
                '}';
    }
}
