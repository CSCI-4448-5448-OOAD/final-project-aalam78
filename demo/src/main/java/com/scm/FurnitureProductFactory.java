package com.scm;
// Concrete FurnitureProductFactory class
class FurnitureProductFactory implements AbstractProductFactory {
    @Override
    public Product createProduct(int productID, String name, String description, float price, float productWeight) {
        return new FurnitureProduct(productID, name, description, price, productWeight);
    }
}