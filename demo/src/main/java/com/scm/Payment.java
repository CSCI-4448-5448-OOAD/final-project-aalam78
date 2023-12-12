package com.scm;

// Payment class using the strategy pattern
public class Payment {
    private String paymentType;
    private PaymentStrategy paymentStrategy;

    public Payment(String paymentType) {
        this.paymentType = paymentType;
    }

    public void setPaymentStrategy(PaymentStrategy paymentStrategy) {
        this.paymentStrategy = paymentStrategy;
    }
    
    public void processPayment(float amount) {
        System.out.println("Processing payment for " + paymentType);

        if (paymentStrategy != null) {
            paymentStrategy.makePayment(amount);
        } else {
            System.out.println("Error: Payment strategy not set. Unable to process payment.");
        }
    }
}