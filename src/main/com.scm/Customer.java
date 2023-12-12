package com.scm

public class Customer extends User {
    private String customerName;
    private Address address;
    private String shippingInfo;

    // Constructor
    public Customer(String customerName, Address address, String shippingInfo) {
        this.customerName = customerName;
        this.address = address;
        this.shippingInfo = shippingInfo;
    }

    // Methods
    public void updateProfile() {
        // Logic to update customer profile
        System.out.println("Customer profile updated for " + customerName);
    }

    public String getOrderStatus() {
        // Logic to retrieve order status
        return "Order status for " + customerName;
    }

    public void changeAddress(Address newAddress) {
        this.address = newAddress;
        System.out.println("Address changed for " + customerName);
    }

    public void setCustomerName(String newCustomerName) {
        this.customerName = newCustomerName;
        System.out.println("Customer name updated to " + newCustomerName);
    }

}