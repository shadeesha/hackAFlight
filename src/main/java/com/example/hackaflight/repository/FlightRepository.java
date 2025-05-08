package com.example.hackaflight.repository;

import com.example.hackaflight.model.core.Flight;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FlightRepository extends JpaRepository<Flight, Long> {
}
