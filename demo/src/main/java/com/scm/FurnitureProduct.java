package com.scm;
// Furniture Product class
class FurnitureProduct extends Product {
    // Additional properties and methods specific to furniture products
    public FurnitureProduct(String name, String description, float price,
                            float productWeight) {
        super(name, description, price, productWeight);
        this.warranty = 5;
    }
}