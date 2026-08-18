package com.example.demo.service;

import org.springframework.stereotype.Service;

import com.example.demo.dto.PaymentResponse;

@Service
public class PaymentService {

    public PaymentResponse processPayment(Long orderId) throws InterruptedException {

        
        return new PaymentResponse(orderId, "SUCCESS");
    }
}