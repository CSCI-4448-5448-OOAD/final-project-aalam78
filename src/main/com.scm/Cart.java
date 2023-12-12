package com.scm

import java.util.ArrayList;
import java.util.List;

public class Cart {
    private int cartID;
    private int dateAdded;
    private float total;
    private List<Product> shoppingList;

    // Constructor
    public Cart(int cartID) {
        this.cartID = cartID;
        this.dateAdded = (int) System.currentTimeMillis(); // Using current time as an example
        this.total = 0.0f;
        this.shoppingList = new ArrayList<>();
    }

    // Method to add a product to the cart
    public void addCartItem(Product product) {
        shoppingList.add(product);
        updateTotal();
    }

    // Method to remove a product from the cart
    public void removeFromCart(Product product) {
        shoppingList.remove(product);
        updateTotal();
    }

    // Method to update the quantity of a product in the cart
    public void updateQuantity(Product product, int newQuantity) {
        // Implement logic to update the quantity of the specified product
        // For simplicity, let's assume each product has a quantity attribute
        product.setQuantity(newQuantity);
        updateTotal();
    }

    // Method to view the contents of the cart
    public void viewCart() {
        System.out.println("Cart ID: " + cartID);
        System.out.println("Date Added: " + dateAdded);
        System.out.println("Total: $" + total);
        System.out.println("Shopping List:");
        for (Product product : shoppingList) {
            System.out.println(product.toString());
        }
    }

    // Method to perform checkout
    public void checkout() {
        // Implement logic for the checkout process
        // For example, update inventory, process payment, etc.
        System.out.println("Checkout completed. Thank you for your purchase!");
        // Reset the cart after checkout
        shoppingList.clear();
        total = 0.0f;
    }

    // Getter for shopping list
    public List<Product> getShoppingList() {
        return shoppingList;
    }

    // Private method to update the total based on the products in the cart
    private void updateTotal() {
        total = 0.0f;
        for (Product product : shoppingList) {
            total += product.getPrice() * product.getQuantity();
        }
    }

}

