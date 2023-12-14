package com.scm;

import com.scm.ApiController.ProductRequest;

import java.util.Map;

public class ApiControllerTest {

    public static void main(String[] args) {
        ApiController apiController = new ApiController();

        int customerID = 1;
        ProductDAO productDAO = ProductDAO.getInstance();
        productDAO.clearPreviousEntries();
        OrderDAO orderDAO = OrderDAO.getInstance();
        orderDAO.clearPreviousEntries(customerID);

        // Test listing a MacBook Pro
        int electronicProducTID = testListProduct(apiController,
                "MacBook Pro 2023",
                "M1 MacBook", 100,
                3, 1, "Electronic");

        // Test listing an Office Chair
        int furnitureProductID = testListProduct(apiController, "Chair",
                "Office " + "Chair",
                50, 20, 2, "Furniture");

        // Test adding items to the cart
        testAddItemToCart(apiController, customerID, electronicProducTID); // Assuming customer_id 1 and productID 1 exist
        testAddItemToCart(apiController, customerID, furnitureProductID); // Assuming
        // customer_id 1 and productID 1 exist
        int orderID = testCheckout(apiController, customerID);
        // Test getting order status
        testGetOrderStatus(apiController, customerID, orderID);
    }

    private static String testGetOrderStatus(ApiController apiController,
                                             int customerID, int orderID) {
        // Call the getOrderStatus endpoint
        System.out.println("\nGetting order status for Customer ID: " +
                customerID + " and Order ID: " + orderID);
        String orderStatus = apiController.getOrderStatus(customerID, orderID);
        System.out.println("Order Status: " + orderStatus);
        return orderStatus;
    }

    private static void testAddItemToCart(ApiController apiController,
                                          int customerID, int productID) {
        // Call the addItemToCart endpoint

        Map<String, Integer> request = Map.of("customerID", customerID,
                "productID", productID);
        boolean addItemResult = apiController.addItemToCart(request);

        if (addItemResult) {
            System.out.println("Item successfully added to the cart for  " +
                    "customer ID " + customerID);
        } else {
            System.out.println("Failed to add item to the cart for customer  " +
                    "ID " + customerID);
        }
        System.out.println();
    }

    private static int testCheckout(ApiController apiController, int customerID) {
        // Call the checkout endpoint
        System.out.println("Checking out for Customer ID: " + customerID);
        int orderID = apiController.checkout(customerID);
        System.out.println("Order ID: " + orderID);
        return orderID;
    }

    private static int testListProduct(ApiController apiController,
                                        String name, String description,
                                        float price, float productWeight,
                                        int warranty, String productType) {
        // Create a ProductRequest object
        ProductRequest productRequest = new ProductRequest();
        productRequest.setName(name);
        productRequest.setDescription(description);
        productRequest.setPrice(price);
        productRequest.setProductWeight(productWeight);
        productRequest.setWarranty(warranty);
        productRequest.setProductType(productType);

        // Call the listProduct endpoint
        int productID = apiController.listProduct(productRequest);
        System.out.println("Listed product with Product ID: " + productID);
        return productID;
    }
}
