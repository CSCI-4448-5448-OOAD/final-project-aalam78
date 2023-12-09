package com.scm;

import java.beans.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DatabaseConnection {

    public static void main(String[] args) {
        // JDBC URL, username, and password of MySQL server
        String url = "jdbc:mysql://localhost:3306/OOAD-Final";
        String user = "root";
        String password = "Marshmallow42069$$";

        try {
            // Establish a connection
            try (Connection connection = DriverManager.getConnection(url, user, password)) {
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
        try (java.sql.Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(showTablesQuery)) {
            while (resultSet.next()) {
                System.out.println(resultSet.getString(1));
            }
        }
    }

    private static void handleSQLException(SQLException e) {
        System.err.println("SQL Exception occurred:");
        System.err.println("SQL State: " + e.getSQLState());
        System.err.println("Error Code: " + e.getErrorCode());
        System.err.println("Message: " + e.getMessage());
        e.printStackTrace();
    }
}
