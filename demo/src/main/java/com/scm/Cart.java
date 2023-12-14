package com.scm;

import java.time.Instant;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.time.format.DateTimeFormatter;

public class Cart {
    private int cartID;
    private long dateAdded; // Changed to long for timestamp
    private float total;
    private List<CartItem> shoppingList; // Changed to a list of CartItem

    // Constructor
    public Cart(int cartID) {
        this.cartID = cartID;
        // Using current time as the date of running the application
        this.dateAdded = System.currentTimeMillis();
        this.total = 0.0f;
        this.shoppingList = new ArrayList<>();
    }

    public float getTotalCost() {
        return total;
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
    public boolean removeFromCart(Product product) {
        CartItem cartItem = findCartItem(product);
        if (cartItem != null) {
            shoppingList.remove(cartItem);
            updateTotal();
            return true;
        }
        return false;
    }

    // Method to read cart items at any given time
    public void readCartItems() {
        for (CartItem cartItem : shoppingList) {
            System.out.println(cartItem.getProduct().getProductName() + " " +
                    cartItem.getQuantity());
        }
    }

    // Method to update the quantity of a product in the cart
    public void updateQuantity(Product product, int newQuantity) {
        CartItem cartItem = findCartItem(product);
        if (cartItem != null && newQuantity >= 0) {
            cartItem.setQuantity(newQuantity);
            updateTotal();
        }
    }

    // Getter for formatted date
    public String getFormattedDate() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(
                "yyyy-MM-dd HH:mm:ss");
        return Instant.ofEpochMilli(dateAdded).atZone(
                ZoneId.systemDefault()).toLocalDateTime().format(formatter);
    }

    // Method to view the contents of the cart
    public void viewCart() {
//        System.out.println("Cart ID: " + cartID);
        if (shoppingList.isEmpty()) {
        System.out.println("Shopping List: Empty");
        } else {
            System.out.println("Date & Time Added: " + getFormattedDate());
            System.out.println("Total: $" + total);
            System.out.println("Shopping List: ");
            for (CartItem cartItem : shoppingList) {
                System.out.println(cartItem.toString());
            }
        }
    }


    // Getter for shopping list
    public List<CartItem> getShoppingList() {
        return shoppingList;
    }

    // method to update the total based on the products in the cart
    public void updateTotal() {
        total = 0.0f;
        for (CartItem cartItem : shoppingList) {
            total += cartItem.getTotalCost();
        }
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

    public void clearCart() {
        shoppingList.clear();
        updateTotal();
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
            return this.product;
        }

        public int getQuantity() {
            return this.quantity;
        }



        public void setQuantity(int quantity) {
            this.quantity = quantity;
        }

        public float getTotalCost() {
            return product.getProductPrice() * quantity;
        }

        @Override
        public String toString() {
            return "CartItem{" +
                    "product=" + product +
                    ", quantity=" + quantity +
                    ", subtotal=$" + getTotalCost() +
                    '}';
        }
    }
}
