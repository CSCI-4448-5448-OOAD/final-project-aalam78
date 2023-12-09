package com.scm;

import java.util.HashMap;
import java.util.List;

public class Product {
    private int productID;
    private String name;
    private String description;
    private float price;
    private float productWeight;


    public static HashMap<Integer, Product> productMap = new HashMap<>();


    // Constructor
    public Product(int productID, String name, String description, float price, float productWeight) {
        if (productID < 0 || price < 0 || productWeight < 0) {
            throw new IllegalArgumentException("Invalid product parameters");
        }
        this.productID = productID;
        this.name = name;
        this.description = description;
        this.price = price;
        this.productWeight = productWeight;
    }

    // Getter methods

    public String getProductName() {
        return name;
    }

    public float getProductWeight() {
        return productWeight;
    }

    public int getProductID() {
        return productID;
    }

    public float getProductPrice() {
        return price;
    }


    // Method to check if the price is down and trigger an alert
    public boolean priceIsDownAlert(float newPrice) {
        return newPrice < price;
    }

    // Java doesn't support operator overloading, so I've created a separate method for adding to the database
    public static void addProductToDB(Product product, HashMap<Integer, Product> productMap) {
        productMap.put(product.getProductID(), product);
    }

    public static void removeProductFromDB(Product product, HashMap<Integer, Product> productMap) {
        productMap.remove(product.getProductID());
    }

    // Method to get a product from the database based on productID
    public static Product getProductFromDB(int productID, HashMap<Integer, Product> productMap) {
        return productMap.get(productID);
    }
    @Override
    public String toString() {
        return "Product ID: " + productID +
                ", Name: " + name +
                ", Description: " + description +
                ", Price: " + price +
                ", Weight: " + productWeight;}

    public static List<Product> getAllProducts() {
        return null;
    }

    public static Product createProduct(Product product) {
        return null;
    }
}