package com.scm;

import java.util.ArrayList;
import java.util.List;

public class Cart {
    private int cartID;
    private long dateAdded; // Changed to long for timestamp
    private float total;
    private List<CartItem> shoppingList; // Changed to a list of CartItem
    private PaymentStrategy paymentStrategy; // Field to hold the payment strategy

    // Constructor
    public Cart(int cartID) {
        this.cartID = cartID;
        this.dateAdded = System.currentTimeMillis(); // Using current time as an example
        this.total = 0.0f;
        this.shoppingList = new ArrayList<>();
        this.paymentStrategy = null; // Initialize the payment strategy to null
    }

    // Method to add a product to the cart
    public void addCartItem(Product product, int quantity) {
        // Check if the product is already in the cart
        CartItem existingItem = findCartItem(product);

        if (existingItem != null) {
            existingItem.setQuantity(existingItem.getQuantity() + quantity);
        } else {
            CartItem newItem = new CartItem(product, quantity);
            shoppingList.add(newItem);
        }

        updateTotal();
    }

    // Method to remove a product from the cart
    public void removeFromCart(Product product) {
        CartItem cartItem = findCartItem(product);
        if (cartItem != null) {
            shoppingList.remove(cartItem);
            updateTotal();
        }
    }

    // Method to update the quantity of a product in the cart
    public void updateQuantity(Product product, int newQuantity) {
        CartItem cartItem = findCartItem(product);
        if (cartItem != null) {
            cartItem.setQuantity(newQuantity);
            updateTotal();
        }
    }

    // Method to view the contents of the cart
    public void viewCart() {
        System.out.println("Cart ID: " + cartID);
        System.out.println("Date Added: " + dateAdded);
        System.out.println("Total: $" + total);
        System.out.println("Shopping List:");
        for (CartItem cartItem : shoppingList) {
            System.out.println(cartItem.toString());
        }
    }

    // Method to set the payment strategy
    public void setPaymentStrategy(PaymentStrategy paymentStrategy) {
        this.paymentStrategy = paymentStrategy;
    }

    // Getter for shopping list
    public List<CartItem> getShoppingList() {
        return shoppingList;
    }

    // Private method to update the total based on the products in the cart
    private float updateTotal() {
        total = 0.0f;
        for (CartItem cartItem : shoppingList) {
            total += cartItem.getSubtotal();
        }
        return cartID;
    }

    // Method to perform checkout
    public void checkout() {
        if (paymentStrategy == null) {
            System.out.println("Error: Payment strategy not set. Please set a payment strategy.");
            return;
        }

        // Calculate the total amount from the shopping list
        float amount = updateTotal();

        // Use the payment strategy to make the payment
        paymentStrategy.makePayment(amount);

        // Implement logic for the checkout process
        // For example, update inventory, etc.
        System.out.println("Checkout completed. Thank you for your purchase!");

        // Reset the cart after checkout
        shoppingList.clear();
        total = 0.0f;
    }

    // Private method to find a CartItem for a given Product in the cart
    private CartItem findCartItem(Product product) {
        for (CartItem cartItem : shoppingList) {
            if (cartItem.getProduct().getProductID() == product.getProductID()) {
                return cartItem;
            }
        }
        return null;
    }
}

// Create a separate CartItem class to encapsulate product and quantity information
class CartItem {
    private Product product;
    private int quantity;

    public CartItem(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    public Product getProduct() {
        return product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public float getSubtotal() {
        return product.getProductPrice() * quantity;
    }

    @Override
    public String toString() {
        return "CartItem{" +
                "product=" + product +
                ", quantity=" + quantity +
                ", subtotal=$" + getSubtotal() +
                '}';
    }




}
