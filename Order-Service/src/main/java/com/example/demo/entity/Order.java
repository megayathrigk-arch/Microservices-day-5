package com.example.demo.entity;

public class Order {

    private Long orderId;
    private Long userId;
    private String product;
    private double amount;

    // No-args constructor
    public Order() {
    }

    // All-args constructor
    public Order(Long orderId, Long userId, String product, double amount) {
        this.orderId = orderId;
        this.userId = userId;
        this.product = product;
        this.amount = amount;
    }

    // Getters and Setters

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}