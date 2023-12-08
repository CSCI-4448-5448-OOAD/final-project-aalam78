package com.scm;

import java.util.HashMap;
import java.util.List;

public class Stock {
    private List<Product> availableProducts;
    private int amount;
    private HashMap<Integer, Product> amountOfProduct;
    private HashMap<Integer, Stock> stockMap = new HashMap<>();  // Made static;

    // Constructor
    public Stock(List<Product> availableProducts, int amount, HashMap<Integer, Product> amountOfProduct) {
        this.availableProducts = availableProducts;
        this.amount = amount;
        this.amountOfProduct = amountOfProduct;
    }

    // Methods
    public Stock getStock(int stockID) {
        return stockMap.get(stockID);
    }

    public boolean isAvailable(Product product) {
        return availableProducts.contains(product);
    }

    public void updateStock(Product product) {
        if (amountOfProduct.containsKey(product.getProductID())) {
            amount++;
            amountOfProduct.put(product.getProductID(), product);
        } else {
            // Handle the case when the product is not in the stock
            System.out.println("Product not found in stock.");
        }

    }

    public void contactSupplier(Product product, int quantity) {
        // Logic to contact the supplier for more products
        // This is a placeholder and needs to be implemented based on your requirements
        System.out.println("Contacting supplier for " + quantity + " units of " + product.getProductName());
    }

    public boolean backToStockAlert(int threshold) {
        // Logic to check if the stock is below a certain threshold and trigger an alert
        boolean alert = amount < threshold;
        if (alert) {
            System.out.println("Stock is below threshold. Alert!");
        }
        return alert;
    }

    public void addStockToDB(int stockID, Stock stock) {
        stockMap.put(stockID, stock);
    }
}
