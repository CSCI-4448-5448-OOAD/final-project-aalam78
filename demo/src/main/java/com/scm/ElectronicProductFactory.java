package com.scm;
// Concrete ElectronicProductFactory class
class ElectronicProductFactory implements AbstractProductFactory {
    @Override

    public Product createProduct(String name, String description, float price, float productWeight) {
        return new ElectronicProduct(name, description, price, productWeight);
    }
}