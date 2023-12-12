package com.scm;

import java.sql.*;

public class DatabaseConnection {

    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/ooad-final-db";
    private static final String USER = "root";
    private static final String PASSWORD = "Marshmallow42069$$";

    private static Connection connection;

    private DatabaseConnection() {
        // Private constructor to prevent instantiation
    }

    public static Connection getConnection() {
        if (connection == null) {
            createSingletonConnection();
        }
        return connection;
    }

    private static void createSingletonConnection() {
        try {
            connection = DriverManager.getConnection(JDBC_URL, USER, PASSWORD);
        } catch (SQLException e) {
            handleSQLException(e);
        }
    }

    public static void main(String[] args) {
        try {
            // Establish a connection
            try (Connection connection = getConnection()) {
                // Do something with the connection (e.g., execute SQL queries)
                // Example: show existing tables
                showTables(connection);
            } catch (SQLException e) {
                handleSQLException(e);
            }
        } catch (Exception e) {
            System.err.println("Unexpected error occurred.");
            e.printStackTrace();
        }
    }


    private static void showTables(Connection connection) throws SQLException {
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
    
    static void handleSQLException(SQLException e) {
        System.err.println("SQL Exception occurred:");
        System.err.println("SQL State: " + e.getSQLState());
        System.err.println("Error Code: " + e.getErrorCode());
        System.err.println("Message: " + e.getMessage());
        e.printStackTrace();
    }
}