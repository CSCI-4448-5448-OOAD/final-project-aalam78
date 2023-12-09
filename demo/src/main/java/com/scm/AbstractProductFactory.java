package com.scm;

import java.util.HashMap;

// Product interface
interface Product {
    int getProductID();

    String getProductName();

    String getDescription();

    float getProductWeight();

    float getProductPrice();

    String toString();
}

// Concrete product for furniture
class Furniture implements Product {
    private int productID;
    private String name;
    private String description;
    private float price;
    private float productWeight;
    private String color;

    public Furniture(int productID, String name, String description, float price, float productWeight, String color) {
        this.productID = productID;
        this.name = name;
        this.description = description;
        this.price = price;
        this.productWeight = productWeight;
        this.color = color;
    }

    @Override
    public int getProductID() {
        return productID;
    }

    @Override
    public String getProductName() {
        return name;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public float getProductWeight() {
        return productWeight;
    }

    @Override
    public float getProductPrice() {
        return price;
    }

    public String getColor() {
        return color;
    }

    @Override
    public String toString() {
        return "Product ID: " + productID +
                ", Name: " + name +
                ", Description: " + description +
                ", Price: " + price +
                ", Weight: " + productWeight +
                ", Color: " + color;
    }
}

// Concrete product for electronics
class Electronic implements Product {
    private int productID;
    private String name;
    private String description;
    private float price;
    private float productWeight;
    private int powerConsumption;

    public Electronic(int productID, String name, String description, float price, float productWeight, int powerConsumption) {
        this.productID = productID;
        this.name = name;
        this.description = description;
        this.price = price;
        this.productWeight = productWeight;
        this.powerConsumption = powerConsumption;
    }

    @Override
    public int getProductID() {
        return productID;
    }

    @Override
    public String getProductName() {
        return name;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public float getProductWeight() {
        return productWeight;
    }

    @Override
    public float getProductPrice() {
        return price;
    }

    public int getPowerConsumption() {
        return powerConsumption;
    }

    @Override
    public String toString() {
        return "Product ID: " + productID +
                ", Name: " + name +
                ", Description: " + description +
                ", Price: " + price +
                ", Weight: " + productWeight +
                ", Power Consumption: " + powerConsumption;
    }
}

// Abstract Factory interface
interface AbstractProductFactory {
    Product createProduct(int productID, String name, String description, float price, float productWeight);
}

// Concrete implementation of the AbstractProductFactory
class ConcreteProductFactory implements AbstractProductFactory {
    @Override
    public Product createProduct(int productID, String name, String description, float price, float productWeight) {
        // Assuming product type is determined based on some criteria (e.g., category)
        // For simplicity, let's assume even product IDs represent furniture and odd product IDs represent electronics
        if (productID % 2 == 0) {
            // For furniture, you can provide a default color (you can modify this based on your requirement)
            return new Furniture(productID, name, description, price, productWeight, "DefaultColor");
        } else {
            // For electronics, you can provide a default power consumption (you can modify this based on your requirement)
            return new Electronic(productID, name, description, price, productWeight, 0);
        }
    }
}
