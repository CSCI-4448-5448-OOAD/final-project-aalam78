package com.scm;

import java.sql.*;
import java.util.HashMap;

import static com.scm.DatabaseConnection.getConnection;
import static com.scm.DatabaseConnection.handleSQLException;

public class ProductDAO {
    // Method to insert a product into the database
    public void addProductToDB(Product product, HashMap<Integer, Product> productMap) {
        int productID = insertProduct(product.getProductName(),
                product.getProductDescription(), product.getProductPrice(),
                product.getProductWeight(), product.getWarranty());
        if (productID != -1) {
            productMap.put(product.getProductID(), product);
        }
    }

    public static int insertProduct(String name,
                                     String  description, double price,
                                     double productWeight, int warranty) {
        String selectQuery = "SELECT * FROM product WHERE Name = ?";
        String insertQuery = "INSERT INTO product (Name, " +
                "Description, Price, ProductWeight, Warranty) VALUES (?, ?, " +
                "?, ?, ?)";

        try {
            Connection connection = getConnection();
            PreparedStatement selectStatement =
                    connection.prepareStatement(selectQuery);
            selectStatement.setString(1, name);
            ResultSet resultSet = selectStatement.executeQuery();
            if (resultSet.next()) {
                // Product already exists, you can choose to skip
                // In future we can have the criteria of if a product with
                // same IMEI exists, since two laptops can have all the same
                // specs but their IMEI will be different.
                System.out.println("Product with Name " + name + " already " +
                        "exists.  Skipping insertion.");
                return -1;
            }

            PreparedStatement preparedStatement =
                    connection.prepareStatement(insertQuery,
                            Statement.RETURN_GENERATED_KEYS);

            // Set parameters for the prepared statement
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, description);
            preparedStatement.setDouble(3, price);
            preparedStatement.setDouble(4, productWeight);
            preparedStatement.setInt(5, warranty);

            // Execute the insert query
            preparedStatement.executeUpdate();


            // Retrieve the generated ProductID
            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                // Check if the entry exists by querying the database
                checkInsertedProduct(connection);
                System.out.println("Product inserted successfully!");
                return generatedKeys.getInt(1);
            } else {
                throw new SQLException("Creating product failed, no ProductID" +
                        "  obtained.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    private static void checkInsertedProduct(Connection connection) {
        String selectQuery = "SELECT * FROM product WHERE ProductID = " +
                "LAST_INSERT_ID()";

        try {
            PreparedStatement preparedStatement =
                    connection.prepareStatement(selectQuery);
//            preparedStatement.setInt(1, productId);

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

                Product product = new Product(name, description, price, productWeight);
                System.out.println(product);
            }

        } catch (SQLException e) {
            handleSQLException(e);
        }
    }
}
