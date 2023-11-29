package com.scm

import java.util.HashMap;
import java.util.Map;

public class Cart {
    private int cartID;
    private int dateAdded;
    private float total;
    private Shopping shopping;

    // Constructor
    public Cart(int cartID, int dateAdded, Shopping shopping) {
        this.cartID = cartID;
        this.dateAdded = dateAdded;
        this.shopping = shopping;
        this.total = 0.0f;
    }

    // Additional methods for the Cart class
    public void addCartItem(Product product) {
        // Assuming product.getPrice() returns the price of the product
        total += product.getPrice();
        shopping.addToCart(product.getProductID());
        System.out.println(product.getProductName() + " added to cart for shopping customer " + shopping.getUserName());
    }

    public void removeFromCart(Product product) {
        // Assuming product.getPrice() returns the price of the product
        total -= product.getPrice();
        shopping.removeFromCart(product.getProductID());
        System.out.println(product.getProductName() + " removed from cart for shopping customer " + shopping.getUserName());
    }

    public void updateQuantity() {
        // Logic to update the quantity of items in the cart
        System.out.println("Quantity updated for cart ID " + cartID);
    }

    public void viewCart() {
        HashMap<Integer, Product> cartItems = shopping.getCart();
        System.out.println("Shopping cart for customer " + shopping.getUserName() + " (Cart ID: " + cartID + "):");
        for (Map.Entry<Integer, Product> entry : cartItems.entrySet()) {
            Product product = entry.getValue();
            System.out.println("  - " + product.getProductName() + " | Price: $" + product.getPrice());
        }
        System.out.println("Total: $" + total);
    }

    public void checkout() {
        // Logic to process the checkout, e.g., update order history, clear the cart, etc.
        System.out.println("Checkout completed for cart ID " + cartID);
    }

    public HashMap<Integer, Product> getShoppingList() {
        return shopping.getCart();
    }

    public static void main(String[] args) {
        // Example usage
        Shopping shoppingCustomer = new Shopping(1, "John Doe", "john@example.com", "password123", currentDate, address, "Express Shipping");
        Cart shoppingCart = new Cart(1, currentDate, shoppingCustomer);

        // Adding a product to the cart
        Product product = new Product(1, "Laptop", "High-performance laptop", 1200.0f, 2.5f);
        shoppingCart.addCartItem(product);

        // Viewing the cart
        shoppingCart.viewCart();

        // Removing a product from the cart
        shoppingCart.removeFromCart(product);

        // Checking out
        shoppingCart.checkout();
    }
}
