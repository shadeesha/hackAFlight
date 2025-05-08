package com.example.hackaflight.model.core;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "tairline")
public class Airline {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column
    private String code;

    @Column
    private String country;

    public Airline() {

    }

    public Airline(Long id, String name, String code, String country) {
        this.id = id;
        this.name = name;
        this.code = code;
        this.country = country;
    }

    public Airline(String country, String code, String name) {
        this.country = country;
        this.code = code;
        this.name = name;
    }

    @Override
    public String toString() {
        return "Airline{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", code='" + code + '\'' +
                ", country='" + country + '\'' +
                '}';
    }
}
