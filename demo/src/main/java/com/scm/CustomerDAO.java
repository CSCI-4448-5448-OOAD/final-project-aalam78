package com.scm;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class CustomerDAO {

    private static final String SELECT_CUSTOMER_BY_ID = "SELECT * FROM " +
            "customers WHERE user_id = ?";

    // Assuming you have a method to get a database connection
    private Connection getConnection() {
        return DatabaseConnection.getConnection();
    }

    public Shopping getShopperById(int userId) {
        try {
            Connection connection = getConnection();
             PreparedStatement preparedStatement =
                     connection.prepareStatement(SELECT_CUSTOMER_BY_ID);

            preparedStatement.setInt(1, userId);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return extractShopperFromResultSet(resultSet);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null; // Return null if customer not found or an error occurred
    }


    private Shopping extractShopperFromResultSet(ResultSet resultSet) throws SQLException {
        int userId = resultSet.getInt("user_id");
        String userName = resultSet.getString("user_name");
        String email = resultSet.getString("email");
        String password = resultSet.getString("password");
        Date registerDate = resultSet.getDate("register_date");
        String customerName = resultSet.getString("customer_name");
        String shippingInfo = resultSet.getString("shipping_info");

        // Assuming you have an Address constructor
        Address address = new Address(
                resultSet.getString("address_country"),
                resultSet.getString("address_city"),
                resultSet.getString("address_street"),
                resultSet.getString("address_apartment"),
                resultSet.getString("address_zip_code")
        );

        return new Shopping(userId, userName, email, password, registerDate,
                shippingInfo, customerName, address);
    }

}
