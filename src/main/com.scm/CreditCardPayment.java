package com.scm
// Concrete CreditCardPayment class implementing PaymentStrategy
class CreditCardPayment implements PaymentStrategy {
    private String cardNumber;
    private String expiryDate;
    private String cvv;

    public CreditCardPayment(String cardNumber, String expiryDate, String cvv) {
        this.cardNumber = cardNumber;
        this.expiryDate = expiryDate;
        this.cvv = cvv;
    }

    @Override
    public void makePayment(float amount) {
        // Logic to process credit card payment
        System.out.println("Paid $" + amount + " using credit card.");
    }
}