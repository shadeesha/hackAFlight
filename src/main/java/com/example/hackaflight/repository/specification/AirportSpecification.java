package com.example.hackaflight.repository.specification;

import com.example.hackaflight.model.core.Airport;
import org.springframework.data.jpa.domain.Specification;

public class AirportSpecification {

    public static Specification<Airport> hasCode(String code) {
        return (root, query, criteriaBuilder) -> {
            if(code == null) return null;
            return criteriaBuilder.equal(root.get("code"), code);
        };
    }

    public static Specification<Airport> hasName(String name) {
        return (root, query, criteriaBuilder) -> {
            if(name == null) return null;
            return criteriaBuilder.equal(root.get("name"), name);
        };
    }

    public static Specification<Airport> hasCity(String city) {
        return (root, query, criteriaBuilder) -> {
            if(city == null) return null;
            return criteriaBuilder.equal(root.get("city"), city);
        };
    }

    public static Specification<Airport> hasCountry(String country) {
        return (root, query, criteriaBuilder) -> {
            if(country == null) return null;
            return criteriaBuilder.equal(root.get("country"), country);
        };
    }
}
