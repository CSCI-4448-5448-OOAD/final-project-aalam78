package com.scm;
// Concrete FurnitureProductFactory class
class FurnitureProductFactory implements AbstractProductFactory {
    @Override
    public Product createProduct(String name, String description, float price, float productWeight) {
        return new FurnitureProduct(name, description, price, productWeight);
    }
}