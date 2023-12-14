package com.scm;

import java.sql.*;
import java.util.HashMap;

import static com.scm.DatabaseConnection.handleSQLException;

public class ProductDAO {

    private static ProductDAO instance;

    private ProductDAO() {
        // private constructor to prevent instantiation
    }

    public static synchronized ProductDAO getInstance() {
        if (instance == null) {
            instance = new ProductDAO();
        }
        return instance;
    }

    private Connection getConnection() {
        return DatabaseConnection.getConnection();
    }

    void clearPreviousEntries() {
        String deleteQuery = "DELETE FROM product";
        try {
            Connection connection = getConnection();
            PreparedStatement clearEntriesStatement =
                    connection.prepareStatement(deleteQuery);

            clearEntriesStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

        // Method to insert a product into the database
    public int addProductToDB(Product product,
                               HashMap<Integer, Product> productMap) {
        int productID = insertProduct(product.getProductName(),
                product.getProductDescription(), product.getProductPrice(),
                product.getProductWeight(), product.getWarranty());
        if (productID != -1) {
            productMap.put(product.getProductID(), product);
        }
        return productID;
    }

    public Product getProductById(int productID) {
        String selectQuery = "SELECT * FROM product WHERE ProductID = ?";

        System.out.println("Searching for product with ID " + productID);
        try {
            Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(
                    selectQuery);
            preparedStatement.setInt(1, productID);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                // Retrieve product details from the ResultSet
                String name = resultSet.getString("Name");
                String description = resultSet.getString("Description");
                double price = resultSet.getDouble("Price");
                double productWeight = resultSet.getDouble("ProductWeight");

                // Create and return a Product instance
                Product p = new Product(name, description, (float) price,
                        (float) productWeight);
                p.setProductID(productID);
                System.out.println("Product found: " + p);
                return p;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null; // Return null if product with the given ID is not found
    }

    public int insertProduct(String name,
                             String description, double price,
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
                return resultSet.getInt("ProductID");
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

    public void readAllProducts() {

        String selectQuery = "SELECT * FROM product";

        try {
            Connection connection = DatabaseConnection.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(selectQuery);

            while (resultSet.next()) {
                int productID = resultSet.getInt("ProductID");
                String name = resultSet.getString("Name");
                String description = resultSet.getString("Description");
                float price = resultSet.getFloat("Price");
                float productWeight = resultSet.getFloat("ProductWeight");

                Product product = new Product(name, description, price,
                        productWeight);
                product.setProductID(productID);
                System.out.println(product);
            }

        } catch (SQLException e) {
            handleSQLException(e);
        }
    }

    // Method to remove a product from the database (productMap)
    public static void removeProductFromDB(Product product,
                                           HashMap<Integer, Product> productMap) {
        if (productMap.containsKey(product.getProductID())) {
            productMap.remove(product.getProductID());
            System.out.println(
                    "Product removed from the database: " + product.getProductName());
        } else {
            System.out.println(
                    "Product with ID " + product.getProductID() + " not found in the database.");
        }
    }
}
