package com.scm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@SpringBootApplication
public class ScmApplication {

    public static void main(String[] args) {
        SpringApplication.run(ScmApplication.class, args);
    }
}

@RestController
@RequestMapping("/api/cart")
class CartController {
    private final Cart cart;
    private final Map<Integer, Product> productDatabase;  // Assuming you have a global product database

    public CartController(Cart cart, Map<Integer, Product> productDatabase) {
        this.cart = cart;
        this.productDatabase = productDatabase;
    }

    @GetMapping("/products")
    public List<Product> getProductCatalog() {
        // Get products from the global product database
        return List.copyOf(productDatabase.values());
    }

    @PostMapping("/add-to-cart")
    public void addToCart(@RequestBody CartItemRequest cartItemRequest) {
        // Get the product from the global product database
        Product product = productDatabase.get(cartItemRequest.getProductId());

        if (product != null) {
            // Add items to the cart
            cart.addCartItem(product, cartItemRequest.getQuantity());
        } else {
            throw new IllegalArgumentException("Product not found with ID: " + cartItemRequest.getProductId());
        }
    }

    @GetMapping("/view-cart")
    public void viewCart() {
        // Simulate viewing the cart
        cart.viewCart();
    }

    @PostMapping("/checkout")
    public void checkout() {
        // Simulate the checkout process
        cart.checkout();
    }

    static class CartItemRequest {
        private int productId;
        private int quantity;

        // getters and setters

        public int getProductId() {
            return productId;
        }

        public void setProductId(int productId) {
            this.productId = productId;
        }

        public int getQuantity() {
            return quantity;
        }

        public void setQuantity(int quantity) {
            this.quantity = quantity;
        }
    }
}
