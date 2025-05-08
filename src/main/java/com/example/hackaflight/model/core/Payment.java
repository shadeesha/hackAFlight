package com.example.hackaflight.model.core;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "tpayment")
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "booking_id")
    private Booking booking;

    @Column
    private double amount;

    @Column
    private String method;

    @Column
    private String paymentDate;

    @Column
    private String status;

    public Payment(String status, double amount, String paymentDate, String method, Booking booking) {
        this.status = status;
        this.amount = amount;
        this.paymentDate = paymentDate;
        this.method = method;
        this.booking = booking;
    }

    @Override
    public String toString() {
        return "Payment{" +
                "id=" + id +
                ", booking=" + booking +
                ", amount=" + amount +
                ", method='" + method + '\'' +
                ", paymentDate='" + paymentDate + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
