package com.example.hackaflight.repository.specification;

import com.example.hackaflight.model.core.Passenger;
import org.springframework.data.jpa.domain.Specification;

public class PassengerSpecification {

    public static Specification<Passenger> hasFirstName(String firstName) {
        return (root, query, criteriaBuilder) -> {
            if(firstName == null) return null;
            return criteriaBuilder.equal(root.get("firstName"), firstName);
        };
    }

    public static Specification<Passenger> hasEmail(String email) {
        return (root, query, criteriaBuilder) -> {
            if (email == null) return null;
            return criteriaBuilder.equal(root.get("email"), email);
        };
    }

    public static Specification<Passenger> hasPassportNumber(String passportNumber) {
        return (root, query, criteriaBuilder) -> {
            if(passportNumber == null) return null;
            return criteriaBuilder.equal(root.get("passportNumber"), passportNumber);
        };
    }
}
