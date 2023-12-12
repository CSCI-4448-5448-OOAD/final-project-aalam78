package com.scm;

// Electronic Product class
class ElectronicProduct extends Product {
    // Additional properties and methods specific to electronic products
    public ElectronicProduct(String name, String description, float price, float productWeight) {
        super(name, description, price, productWeight);
        this.warranty = 2;
    }
}
