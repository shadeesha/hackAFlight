package com.example.hackaflight.repository;

import com.example.hackaflight.model.support.Baggage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BaggageRepository extends JpaRepository<Baggage, Long> {
}
