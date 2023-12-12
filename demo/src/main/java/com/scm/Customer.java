package com.scm;

import java.util.Date;
public class Customer extends User {
    private String customerName;
    private Address address;
    private String shippingInfo;

    private Cart shoppingCart;

    // Constructor
    public Customer(int userID, String userName, String email,
                    String password,  Date registerDate,
                    String shippingInfo, String customerName, Address address) {
        super(userID, userName, email, password, registerDate);
        this.customerName = customerName;
        this.address = address;
        this.shippingInfo = shippingInfo;
    }

    public String getShippingInfo() {
        return shippingInfo;
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

    @Override
    public String toString() {
        return "Customer{" +
                super.toString() +  // Using super.toString() to include
                // fields  from the superclass
                ", customerName='" + customerName + '\'' +
                ", address= " + address +
                ", shippingInfo='" + shippingInfo + '\'' +
                ", shoppingCart=" + shoppingCart +
                '}';
    }
}