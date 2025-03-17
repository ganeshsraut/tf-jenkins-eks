package com.talentica.workshop.inventory.model;

public class Ingredient {
    private String name;
    private int quantity;

    public Ingredient() {}

    public Ingredient(String name, int quantity) {
        this.name = name;
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void reduceQuantity(int amount) {
        this.quantity -= amount;
    }
}

