package com.scm;
// Abstract Factory interface
interface AbstractProductFactory {
    com.scm.Product createProduct(String name, String description, float price, float productWeight);
}