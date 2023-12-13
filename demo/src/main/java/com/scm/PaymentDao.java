package com.scm;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PaymentDao {
    private static final String SELECT_CUSTOMER_BY_ID = "SELECT email, " +
            "payment_mode" +
            " FROM " +
            "customers WHERE user_id = ?";
    private Connection getConnection() {
        return DatabaseConnection.getConnection();
    }

    public int processPayments(int shopperID) {

        try {
            Connection connection = getConnection();
            PreparedStatement preparedStatement =
                    connection.prepareStatement(SELECT_CUSTOMER_BY_ID);
            preparedStatement.setInt(1, shopperID);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String email = resultSet.getString("email");
                String paymentMode = resultSet.getString(
                        "payment_mode").toLowerCase();

                // Determine payment strategy based on payment mode

                PaymentStrategy paymentStrategy;
                switch (paymentMode) {
                    case "credit card":
                        paymentStrategy = new CreditCardPayment();
                        break;
                    case "pay pal":
                        paymentStrategy = new PayPalPayment(email);
                        break;
                    default:
                        // Handle other payment modes or set a default strategy
                        System.out.println(
                                "Payment mode not supported. " +
                                        "Please select a different payment mode.");
                        continue;
                }

                // Create Payment instance and set the determined PaymentStrategy
                Payment payment = new Payment(paymentMode);
                payment.setPaymentStrategy(paymentStrategy);

                // Process payment (you can use the userId, userName, etc., as needed)
                // Specify the payment amount or fetch it from the database
                Cart cart = Shopping.getICart();
                if (cart != null) {
                    float paymentAmount = cart.getTotalCost();
                    payment.processPayment(paymentAmount);
                } else {
                    System.out.println(
                            "Cart is null. Unable to process payment.");
                }
            }
        } catch (SQLException e) {
            // Log the exception or handle it appropriately
            e.printStackTrace();
        }
        return shopperID;
    }


}
