package com.scm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import static com.scm.ProductDAO.readAllProducts;

@SpringBootApplication
public class ScmApplication {

    public static void main(String[] args) {
        // Initialize the cart
        Cart cart = new Cart(1);

        // Add products to the cart
        ElectronicProductFactory electronicProductFactory = new ElectronicProductFactory();
        Product electronicProduct = electronicProductFactory.createProduct(3, "MacBook Pro 2019", "This laptop is mediocre.", 500, 2.5f);
        cart.addCartItem(electronicProduct, 2);

        // Set the payment strategy (you can choose between CreditCardPayment or PayPalPayment)
        PaymentStrategy paymentStrategy = new CreditCardPayment("1234-5678-9101-1121", "12/24", "123");
        cart.setPaymentStrategy(paymentStrategy);

        // View the cart contents before checkout
        cart.viewCart();

        // Perform checkout
        cart.checkout();

        // After checkout, the cart will be reset
        cart.viewCart();

        // If you want to read and display all products, you can uncomment the following line
        // readAllProducts();
    }
}
















/* 

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
*/