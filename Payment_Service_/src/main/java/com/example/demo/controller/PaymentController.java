package com.example.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.PaymentResponse;

@RestController
@RequestMapping("/payments")
public class PaymentController {

    @GetMapping("/{orderId}")
    public PaymentResponse processPayment(
            @PathVariable Long orderId) {

        System.out.println("Processing payment for order: " + orderId);

        return new PaymentResponse(
                orderId,
                "SUCCESS"
        );
    }
}