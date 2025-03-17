package com.talentica.workshop.order.model;


public class Order {
    private String coffeeType;
    private int quantity;
    private String status;

    public Order(String coffeeType, int quantity, String status) {
        this.coffeeType = coffeeType;
        this.quantity = quantity;
        this.status = status;
    }

    public String getCoffeeType() {
        return coffeeType;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getStatus() {
        return status;
    }
}
