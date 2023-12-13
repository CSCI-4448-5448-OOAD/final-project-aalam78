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

    public void placeOrder(Shopping shopper) {
        if (shopper.getICart().getShoppingList().isEmpty()) {
            System.out.println("Shopping cart is empty. Please add items to " +
                    "the cart before placing the order.");
            return;
        }

        try {
            Connection connection = getConnection();
            connection.setAutoCommit(false);

            // Insert new order into order_table
            int orderID = insertOrder(shopper);

            // Insert items into order_item
            insertOrderItems(connection, shopper, orderID);

            // Commit the transaction
            connection.commit();

            // Notify the customer about the order placement or update the order status as needed
            System.out.println(
                    "Order placed successfully! Order ID: " + orderID);
        } catch (SQLException e) {
            e.printStackTrace();

        }
    }

    void clearPreviousEntries(int userID) {
        // Clear previous entries in order_item for the user
        String clearOrderItemQuery = "DELETE FROM order_item WHERE orderID IN " +
                "(SELECT orderID FROM order_table WHERE customerID = ?)";

        // Clear previous entries in order_table for the user
        String clearOrderQuery = "DELETE FROM order_table WHERE customerID = ?";

        try {
            Connection connection = getConnection();
            PreparedStatement clearOrderItemStatement =
                    connection.prepareStatement(clearOrderItemQuery);
            PreparedStatement clearOrderStatement =
                    connection.prepareStatement(clearOrderQuery);

            // Set parameters and execute the order_item deletion query
            clearOrderItemStatement.setInt(1, userID);
            clearOrderItemStatement.executeUpdate();

            // Set parameters and execute the order_table deletion query
            clearOrderStatement.setInt(1, userID);
            clearOrderStatement.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private int insertOrder(Shopping shopper)  {
        String insertOrderQuery = "INSERT INTO order_table ( " +
                "dateCreated, totalCost, paymentType, shippingInfo, " +
                "orderStatus, customerID) VALUES (?, ?, ?, ?, ?, ?)";

        try {
            Connection connection = getConnection();
            connection.setAutoCommit(false);
            PreparedStatement insertOrderStatement = connection.prepareStatement(
                    insertOrderQuery,
                    Statement.RETURN_GENERATED_KEYS);


            // Set parameters for the order_table

            insertOrderStatement.setDate(1,
                    new java.sql.Date(new Date().getTime()));
            insertOrderStatement.setFloat(2, shopper.getICart().getTotalCost());
            insertOrderStatement.setString(3, "Credit Card");
            insertOrderStatement.setString(4, shopper.getShippingInfo());
            insertOrderStatement.setString(5, "Pending");
            insertOrderStatement.setInt(6, shopper.getUserID());

            // Execute the insert query for order_table
            int orderID;
            int affectedRows = insertOrderStatement.executeUpdate();

            if (affectedRows > 0) {
                ResultSet generatedKeys = insertOrderStatement.getGeneratedKeys();
                if (generatedKeys.next()) {
                    orderID = generatedKeys.getInt(1);
                    return orderID;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    private void insertOrderItems(Connection connection, Shopping shopper,
                                  int orderID) throws SQLException {
        String insertOrderItemQuery = "INSERT INTO order_item (orderID, " +
                "productID, quantity, itemCost) VALUES (?, ?, ?, ?)";

        try {
            PreparedStatement insertOrderItemStatement =
                    connection.prepareStatement(insertOrderItemQuery);
            // Set parameters for the order_item
            for (Cart.CartItem cartItem : shopper.getICart().getShoppingList()) {
                Product product = cartItem.getProduct();

                insertOrderItemStatement.setInt(1, orderID);
                insertOrderItemStatement.setInt(2, product.getProductID());
                insertOrderItemStatement.setInt(3, cartItem.getQuantity());
                insertOrderItemStatement.setFloat(4, cartItem.getTotalCost());

                // Execute the insert query for order_item
                insertOrderItemStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    // read order by orderID

    public void readAllOrders() {
        String selectAllOrdersQuery = "SELECT * FROM order_table";

        try (Connection connection = getConnection();
             PreparedStatement selectAllOrdersStatement = connection.prepareStatement(
                     selectAllOrdersQuery);
             ResultSet resultSet = selectAllOrdersStatement.executeQuery()) {

            while (resultSet.next()) {
                // Extract data from the result set if needed
                int orderId = resultSet.getInt("orderID");
                Date dateCreated = resultSet.getDate("dateCreated");
                float totalCost = resultSet.getFloat("totalCost");
                String paymentType = resultSet.getString("paymentType");
                String shippingInfo = resultSet.getString("shippingInfo");
                String orderStatus = resultSet.getString("orderStatus");
                int customerId = resultSet.getInt("customerID");
                System.out.println("-----------------------------");
                System.out.println("Order ID: " + orderId);
                System.out.println("Date Created: " + dateCreated);
                System.out.println("Total Cost: " + totalCost);
                System.out.println("Payment Type: " + paymentType);
                System.out.println("Shipping Info: " + shippingInfo);
                System.out.println("Order Status: " + orderStatus);
                System.out.println("Customer ID: " + customerId);
                System.out.println("-----------------------------");
            }
            // Extract other fields...

        } catch (SQLException e) {
            e.printStackTrace();
            // Handle SQLException
        }
    }
}

// Update total quantity in order_item for a specific productID

