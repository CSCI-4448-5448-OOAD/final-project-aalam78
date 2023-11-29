package com.scm

import java.util.HashMap;
import java.util.List;

public class Stock {
    private List<Product> availableProduct;
    private int amount;
    private HashMap<Integer, Product> amountOfProduct;
    private HashMap<Integer, Stock> stockMap;

    // Constructors
    public Stock(List<Product> availableProduct, int amount, HashMap<Integer, Product> amountOfProduct) {
        this.availableProduct = availableProduct;
        this.amount = amount;
        this.amountOfProduct = amountOfProduct;
        this.stockMap = new HashMap<>();
    }

    // Methods
    public Stock getStock(int stockID) {
        return stockMap.get(stockID);
    }

    public boolean isAvailable(Product product) {
        return availableProduct.contains(product);
    }

    public void updateStock(Product product) {
        if (amountOfProduct.containsKey(product.getProductID())) {
            amount++;
            amountOfProduct.put(product.getProductID(), product);
        } else {
            // Handle the case when the product is not in the stock
        }
    }

    public void contactSupplier(Product product, int quantity) {
        // Logic to contact the supplier for more products
        // This is a placeholder and needs to be implemented based on your requirements
    }

    public boolean backToStockAlert() {
        // Logic to check if the stock is below a certain threshold and trigger an alert
        // This is a placeholder and needs to be implemented based on your requirements
        return false;
    }

    public static void addStockToDB(int stockID, Stock stock, HashMap<Integer, Stock> stockMap) {
        stockMap.put(stockID, stock);
    }
}
