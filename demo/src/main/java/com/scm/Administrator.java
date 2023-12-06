package com.scm;

import java.util.Date;

public class Administrator extends User {
    private String adminID;

    // Constructor
    public Administrator(int userID, String userName, String email, String password, Date registerDate, String adminID) {
        super(userID, userName, email, password, registerDate);
        this.adminID = adminID;
    }

    // Additional methods for the Administrator class
    public void refundOrder() {
        // Logic for refunding orders
        System.out.println("Order refunded by admin " + getUserName());
    }

    public void updateCatalog() {
        // Logic to update the catalog
        System.out.println("Catalog updated by admin " + getUserName());
    }

    public void manageOrder() {
        // Logic to manage orders
        System.out.println("Orders managed by admin " + getUserName());
    }

    public void manageProduct() {
        // Logic to manage products (delete, add, update)
        System.out.println("Products managed by admin " + getUserName());
    }
}
