/* 

package com.scm;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnectionSingleton {

    // Singleton instance
    private static DatabaseConnection instance;

    // JDBC URL, username, and password of MySQL server
    private String url = "jdbc:mysql://localhost:3306/OOAD-Final";
    private String user = "root";
    private String password = "Marshmallow42069$$";

    // Private constructor to prevent instantiation
    private DatabaseConnectionSingleton() {
    }

    // Method to get the singleton instance
    public static synchronized DatabaseConnection getInstance() {
        if (instance == null) {
            instance = new DatabaseConnection();
        }
        return instance;
    }

    // Method to get the database connection
    public Connection getConnection() {
        try {
            return DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            handleSQLException(e);
            return null;
        }
    }

    public void showTables() {
        try (Connection connection = getConnection()) {
            // Do something with the connection (e.g., execute SQL queries)
            // Example: show existing tables
            showTables(connection);
        } catch (SQLException e) {
            handleSQLException(e);
        }
    }

    private void showTables(Connection connection) throws SQLException {
        // Example: show existing tables
        System.out.println("Existing Tables:");
        String showTablesQuery = "SHOW TABLES";
        try (var statement = connection.createStatement();
             var resultSet = statement.executeQuery(showTablesQuery)) {
            while (resultSet.next()) {
                System.out.println(resultSet.getString(1));
            }
        }
    }

    private void handleSQLException(SQLException e) {
        System.err.println("SQL Exception occurred:");
        System.err.println("SQL State: " + e.getSQLState());
        System.err.println("Error Code: " + e.getErrorCode());
        System.err.println("Message: " + e.getMessage());
        e.printStackTrace();
    }

    /*
    public static void main(String[] args) {
        // Example of using the singleton instance
        DatabaseConnection dbConnection = DatabaseConnection.getInstance();
        dbConnection.showTables();
    } 
    
}

*/
