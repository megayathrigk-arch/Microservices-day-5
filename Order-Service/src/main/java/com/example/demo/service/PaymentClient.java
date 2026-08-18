package com.example.demo.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.demo.dto.PaymentResponse;

@Service
public class PaymentClient {

    private final RestTemplate restTemplate;
    private final String paymentUrl;

    public PaymentClient(
            RestTemplate restTemplate,
            @Value("${payment.service.url}") String paymentUrl) {

        this.restTemplate = restTemplate;
        this.paymentUrl = paymentUrl;
    }

    @Retryable(maxAttempts = 3)
    public PaymentResponse makePayment(Long orderId) {

        System.out.println("Calling Payment Service...");

        return restTemplate.getForObject(
                paymentUrl + "/payments/" + orderId,
                PaymentResponse.class
        );
    }

    @Recover
    public PaymentResponse fallbackPayment(
            Exception exception,
            Long orderId) {

        System.out.println("Payment Service failed after 3 attempts");

        return new PaymentResponse(
                orderId,
                "PAYMENT_FAILED"
        );
    }
}