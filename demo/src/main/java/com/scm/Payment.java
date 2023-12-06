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
        paymentStrategy.makePayment(amount);
    }
}