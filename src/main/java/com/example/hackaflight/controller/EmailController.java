package com.example.hackaflight.controller;

import com.example.hackaflight.configuration.RabbitConfig;
import com.example.hackaflight.dto.BookingDTO;
import com.example.hackaflight.model.core.Booking;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
public class EmailController {

    @Autowired
    private JavaMailSender javaMailSender;

    @RabbitListener(queues = RabbitConfig.QUEUE)
    public void receiveBooking(BookingDTO booking) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(booking.getPassengerEmail());
        message.setSubject("Flight Booking confirmation");
        message.setText("Hi " + booking.getPassengerName()
                + ",\n\nYour booking is confirmed!");
        javaMailSender.send(message);
    }
}
