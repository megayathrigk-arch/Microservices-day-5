package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.demo.dto.OrderResponse;
import com.example.demo.entity.Order;
import com.example.demo.exception.UserServiceUnavailableException;

@Service
public class OrderService {

    private final RestTemplate restTemplate;

    @Value("${user.service.url}")
    private String userServiceUrl;

    private final List<Order> orders = new ArrayList<>();

    public OrderService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;

        orders.add(new Order(
                5001L,
                1001L,
                "Laptop",
                50000
        ));

        orders.add(new Order(
                5002L,
                1002L,
                "Phone",
                25000
        ));

        orders.add(new Order(
                5003L,
                1003L,
                "Keyboard",
                3000
        ));
    }

    public OrderResponse getOrder(Long orderId) {

        Order order = orders.stream()
                .filter(o -> o.getOrderId().equals(orderId))
                .findFirst()
                .orElse(null);

        if (order == null) {
            throw new RuntimeException("Order Not Found");
        }

        try {

            Map<?, ?> user = restTemplate.getForObject(
                    userServiceUrl + "/api/users/" + order.getUserId(),
                    Map.class
            );

            return new OrderResponse(
                    order.getOrderId(),
                    order.getProduct(),
                    order.getAmount(),
                    order.getUserId(),
                    (String) user.get("name"),
                    (String) user.get("email")
            );

        } catch (Exception e) {

            throw new UserServiceUnavailableException(
                    "User Service Unavailable"
            );
        }
    }
}