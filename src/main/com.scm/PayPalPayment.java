package com.scm
// Concrete PayPalPayment class implementing PaymentStrategy
class PayPalPayment implements PaymentStrategy {
    private String email;

    public PayPalPayment(String email) {
        this.email = email;
    }

    @Override
    public void makePayment(float amount) {
        // Logic to process PayPal payment
        System.out.println("Paid $" + amount + " using PayPal.");
    }
}
