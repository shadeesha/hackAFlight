package com.example.hackaflight.repository;

import com.example.hackaflight.model.core.Booking;
import com.example.hackaflight.repository.specification.BookingSpecification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long>, JpaSpecificationExecutor<Booking> {
}
