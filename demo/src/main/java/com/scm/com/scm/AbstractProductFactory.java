package com.scm;
// Abstract Factory interface
interface AbstractProductFactory {
    Product createProduct(int productID, String name, String description, float price, float productWeight);
}