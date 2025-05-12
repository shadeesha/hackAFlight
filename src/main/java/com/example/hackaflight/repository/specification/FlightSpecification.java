package com.example.hackaflight.repository.specification;

import com.example.hackaflight.model.core.Flight;
import org.springframework.data.jpa.domain.Specification;

public class FlightSpecification {

    public static Specification<Flight> hasAirLineId(Long airLineId) {
        return (root, query, criteriaBuilder) -> {
            if(airLineId == null) return null;
            return criteriaBuilder.equal(root.get("airline").get("id"), airLineId);
        };
    }
}
