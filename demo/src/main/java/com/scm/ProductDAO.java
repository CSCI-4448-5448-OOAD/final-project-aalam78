package com.scm;

import java.sql.*;

import static com.scm.DatabaseConnection.getConnection;
import static com.scm.DatabaseConnection.handleSQLException;

public class ProductDAO {
    // Method to insert a product into the database
    public static void insertProduct(int productId, String name,
                                     String  description, double price,
                                     double productWeight) {
        String insertQuery = "INSERT INTO product (ProductID, Name, " +
                "Description, Price, ProductWeight) VALUES (?, ?, ?, ?, ?)";

        try {
            Connection connection = getConnection();
            PreparedStatement preparedStatement =
                    connection.prepareStatement(insertQuery);

            // Set parameters for the prepared statement
            preparedStatement.setInt(1, productId);
            preparedStatement.setString(2, name);
            preparedStatement.setString(3, description);
            preparedStatement.setDouble(4, price);
            preparedStatement.setDouble(5, productWeight);

            // Execute the insert query
            preparedStatement.executeUpdate();

            System.out.println("Product inserted successfully!");

            // Check if the entry exists by querying the database
            checkInsertedProduct(connection, productId);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void checkInsertedProduct(Connection connection,
                                             int productId) {
        String selectQuery = "SELECT * FROM product WHERE ProductID = ?";

        try {
            PreparedStatement preparedStatement =
                    connection.prepareStatement(selectQuery);
            preparedStatement.setInt(1, productId);

            ResultSet resultSet = preparedStatement.executeQuery();

                // Get metadata to get column names
                var resultSetMetaData = resultSet.getMetaData();
                int columnCount = resultSetMetaData.getColumnCount();

                // Print column names
                for (int i = 1; i <= columnCount; i++) {
                    System.out.print(resultSetMetaData.getColumnName(i) + "\t");
                }
                System.out.println();

                // Print each row
                while (resultSet.next()) {
                    for (int i = 1; i <= columnCount; i++) {
                        System.out.print(resultSet.getString(i) + "\t");
                    }
                    System.out.println();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
    }

    public static void readAllProducts() {

        String selectQuery = "SELECT * FROM product";

        try {
            Connection connection = getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(selectQuery);

            while (resultSet.next()) {
                int productID = resultSet.getInt("ProductID");
                String name = resultSet.getString("Name");
                String description = resultSet.getString("Description");
                float price = resultSet.getFloat("Price");
                float productWeight = resultSet.getFloat("ProductWeight");

                Product product = new Product(productID, name, description, price, productWeight);
                System.out.println(product);
            }

        } catch (SQLException e) {
            handleSQLException(e);
        }
    }
}
