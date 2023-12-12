package com.scm;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

public class OrderDAO {
    private Connection getConnection() {
        return DatabaseConnection.getConnection();
    }
    // Place an order and create entries in order_table and order_item
    public void placeOrder(Shopping shopper) {

        if (shopper.getICart().getShoppingList().isEmpty()) {
            System.out.println("Shopping cart is empty. Please add items to " +
                    "the cart before placing the order.");
            return;
        }

        String insertOrderQuery = "INSERT INTO order_table (status, " +
                "dateCreated,  totalCost, paymentType, shippingInfo,  " +
                "orderStatus, customerID) VALUES (?, ?, ?, ?, ?, ?, ?)";

        String insertOrderItemQuery = "INSERT INTO order_item (orderID,  " +
                "productID, quantity, itemCost) " +
                "VALUES (?, ?, ?, ?)";

        try {
            Connection connection = getConnection();
             PreparedStatement insertOrderStatement =
                     connection.prepareStatement(insertOrderQuery,
                     Statement.RETURN_GENERATED_KEYS);
             PreparedStatement insertOrderItemStatement =
                     connection.prepareStatement(insertOrderItemQuery);

            // Set parameters for the order_table
            insertOrderStatement.setString(1,
                    "Pending");
            insertOrderStatement.setDate(2,
                    new java.sql.Date(new Date().getTime()));
            insertOrderStatement.setFloat(3,
                    shopper.getICart().getTotalCost());
            insertOrderStatement.setString(4,
                    "Credit Card");
            insertOrderStatement.setString(5,
                    shopper.getShippingInfo());
            insertOrderStatement.setString(6,
                    "Pending");
            insertOrderStatement.setInt(7,
                    shopper.getUserID());

            // Execute the insert query for order_table
            int orderID;
            int affectedRows = insertOrderStatement.executeUpdate();
            if (affectedRows > 0) {
                try (ResultSet generatedKeys =
                             insertOrderStatement.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        orderID = generatedKeys.getInt(1);

                        // Set parameters for the order_item
                        for (CartItem cartItem :
                                shopper.getICart().getShoppingList()) {
                            Product product = cartItem.getProduct();
                            System.out.println("Executing SQL statement: " + insertOrderItemQuery +
                                    " with parameters: orderID = " + orderID +
                                    ", productID = " + product.getProductID() +
                                    ", quantity = " + cartItem.getQuantity() +
                                    ", itemCost = " + cartItem.getTotalCost());
                            insertOrderItemStatement.setInt(1, orderID);
                            insertOrderItemStatement.setInt(2, product.getProductID());
                            insertOrderItemStatement.setInt(3, cartItem.getQuantity());
                            insertOrderItemStatement.setFloat(4, cartItem.getTotalCost());

                            // Execute the insert query for order_item
                            insertOrderItemStatement.executeUpdate();
                        }

                        // Notify the customer about the order placement or update the order status as needed
                        System.out.println("Order placed successfully! Order" +
                                "ID:  " + orderID);
                    }
                }
            } else {
                System.out.println("Failed to place the order.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

