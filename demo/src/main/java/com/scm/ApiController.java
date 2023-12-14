package com.scm;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class ApiController {

    private Map<Integer, Shopping> customerIdToShopperMap = new HashMap<>();
    @PostMapping("/listProduct")
    public int listProduct(@RequestBody ProductRequest productRequest) {
        // Implement the logic to list a product and return the productID
        AbstractProductFactory factory =
                ProductFactoryProvider.createFactory(
                        productRequest.getProductType());
        Product product = factory.createProduct(
                productRequest.getName(),
                productRequest.getDescription(),
                productRequest.getPrice(),
                productRequest.getProductWeight());

        ProductDAO productDAO = ProductDAO.getInstance();
        int productID = productDAO.addProductToDB(product, Product.productMap);
        productDAO.readAllProducts();

        return productID; // Return the actual productID
    }

    @PostMapping("/addItemToCart")
    public boolean addItemToCart(@RequestBody Map<String, Integer> request) {
        int customerID = request.get("customerID");
        int productID = request.get("productID");
        CustomerDAO customerDAO = new CustomerDAO();
        Shopping shopper;
        if (customerIdToShopperMap.containsKey(customerID)) {
            shopper = customerIdToShopperMap.get(customerID);
        } else {
            shopper = customerDAO.getShopperById(customerID);
            customerIdToShopperMap.put(customerID, shopper);
        }
        if (shopper != null) {
            shopper.addToCart(productID);
            shopper.viewCart();
            return true; // Successfully added to the cart
        }
        return false; // Failed to add to the cart
    }

    @PostMapping("/checkout")
    public int checkout(@RequestParam int customerID) {
        CustomerDAO customerDAO = new CustomerDAO();

        Shopping shopper;
        if (customerIdToShopperMap.containsKey(customerID)) {
            shopper = customerIdToShopperMap.get(customerID);
        } else {
            shopper = customerDAO.getShopperById(customerID);
            customerIdToShopperMap.put(customerID, shopper);
        }

        if (shopper != null) {
            return shopper.checkout(); // returns the order ID.
        }
        return -1; // Failed to check out
    }

    @PostMapping("/getOrderStatus")
    public String getOrderStatus(@RequestParam int customerID, int orderID) {
        OrderDAO orderDAO = OrderDAO.getInstance();
        return orderDAO.getOrderStatus(customerID, orderID);
    }

    // Define the request payload class for /listProduct endpoint
    public static class ProductRequest {
        private String name;
        private String description;
        private float price;
        private float productWeight;
        private int warranty;
        private String productType;

        // Getters
        public String getName() {
            return name;
        }

        public String getDescription() {
            return description;
        }

        public float getPrice() {
            return price;
        }

        public float getProductWeight() {
            return productWeight;
        }

        public String getProductType() {
            return productType;
        }
        public int getWarranty() {
            return warranty;
        }

        // Setters
        public void setName(String name) {
            this.name = name;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public void setPrice(float price) {
            this.price = price;
        }

        public void setProductWeight(float productWeight) {
            this.productWeight = productWeight;
        }

        public void setWarranty(int warranty) {
            this.warranty = warranty;
        }

        public void setProductType(String productType) {
            this.productType = productType;
        }
    }
}
