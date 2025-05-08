package com.example.hackaflight.repository;

import com.example.hackaflight.model.core.Airport;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AirportRepository extends JpaRepository<Airport, Long> {
}
