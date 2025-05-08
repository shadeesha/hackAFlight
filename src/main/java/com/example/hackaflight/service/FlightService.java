package com.example.hackaflight.service;

import com.example.hackaflight.model.core.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public interface FlightService extends JpaRepository<Flight, Long> {
}
