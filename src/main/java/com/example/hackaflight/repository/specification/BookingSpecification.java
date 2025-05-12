package com.example.hackaflight.repository.specification;

import com.example.hackaflight.model.core.Booking;
import org.springframework.data.jpa.domain.Specification;


public class BookingSpecification {

    public static Specification<Booking> hasPassengerId(Long passengerId) {
        return ((root, query, criteriaBuilder) -> {
            if(passengerId == null) return null;
            return criteriaBuilder.equal(root.get("passenger").get("id"), passengerId);
        });
    }

    public static Specification<Booking> hasFlightId(Long flightId) {
        return ((root, query, criteriaBuilder) -> {
           if(flightId == null) return null;
           return criteriaBuilder.equal(root.get("flight").get("id"), flightId);
        });
    }

    public static Specification<Booking> hasBookinDate(String bookingDate) {
        return((root, query, criteriaBuilder) -> {
           if(bookingDate == null) return null;
           return criteriaBuilder.equal(root.get("bookingDate"), bookingDate);
        });
    }
}
