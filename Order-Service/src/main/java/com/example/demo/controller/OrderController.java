package com.example.demo.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.UserClient;
import com.example.demo.dto.OrderResponse;
import com.example.demo.dto.PaymentResponse;
import com.example.demo.dto.UserDto;
import com.example.demo.service.OrderService;
import com.example.demo.service.PaymentClient;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderService orderService;
    private final PaymentClient paymentClient;
    private final UserClient userClient;

    public OrderController(
            OrderService orderService,
            PaymentClient paymentClient,
            UserClient userClient) {

        this.orderService = orderService;
        this.paymentClient = paymentClient;
        this.userClient = userClient;
    }

    // Get Order
    @GetMapping("/{id}")
    public ResponseEntity<OrderResponse> getOrder(
            @PathVariable Long id) {

        OrderResponse response = orderService.getOrder(id);

        if (response == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(response);
    }

    // Order → Payment
    @GetMapping("/payment/{id}")
    public ResponseEntity<PaymentResponse> getPayment(
            @PathVariable Long id) {

        PaymentResponse response = paymentClient.makePayment(id);

        return ResponseEntity.ok(response);
    }

    // Order → User
    @GetMapping("/user/{id}")
    public ResponseEntity<UserDto> getUser(
            @PathVariable Long id) {

        OrderResponse order = orderService.getOrder(id);

        if (order == null) {
            return ResponseEntity.notFound().build();
        }

        UserDto user = userClient.getUserById(order.getUserId());

        return ResponseEntity.ok(user);
    }
}