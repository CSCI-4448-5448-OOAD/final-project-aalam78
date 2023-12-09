package com.scm;
// Abstract Factory interface
interface AbstractProductFactory {
    Product createProduct(String name, String description, float price, float productWeight);
}