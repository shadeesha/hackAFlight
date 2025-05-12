package com.example.hackaflight.repository.specification;

import com.example.hackaflight.model.core.Airline;
import org.springframework.data.jpa.domain.Specification;

public class AirLineSpecification {

    public static Specification<Airline> hasName(String name) {
        return ((root, query, criteriaBuilder) -> {
           if (name == null) return null;
           return criteriaBuilder.equal(root.get("name"), name);
        });
    }

    public static Specification<Airline> hasCode(String code) {
        return ((root, query, criteriaBuilder) -> {
            if (code == null) return null;
            return criteriaBuilder.equal(root.get("code"), code);
        });
    }

    public static Specification<Airline> hasCountry(String country) {
        return ((root, query, criteriaBuilder) -> {
            if (country == null) return null;
            return criteriaBuilder.equal(root.get("country"), country);
        });
    }
}
