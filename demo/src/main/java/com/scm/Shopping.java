package com.scm;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class Shopping extends Customer {

    private List<Product> wishList;
    private List<Order> orderHistory;
    private HashMap<Integer, Product> cart;

    private static Cart icart;

    ProductDAO productDAO;

    // Constructor
    public Shopping(int userID, String userName, String email,
                    String password,  Date registerDate,
                    String shippingInfo, String customerName, Address address) {
        super(userID, userName, email, password, registerDate, shippingInfo,
                customerName, address);
        this.wishList = new ArrayList<>();
        this.cart = new HashMap<>();
        this.productDAO = new ProductDAO();
        this.icart = new Cart(userID);
    }

    public static Cart getICart() {
        return icart;
    }

    public List<Product> getWishList() {
        return wishList;
    }

    public void addToCart(int productID) {
        Product product = productDAO.getProductById(productID);
        if (product != null) {
            icart.addCartItem(product, 1);
            System.out.println(product.getProductName() + " added to cart for" +
                    " "  + getUserName());
        } else {
            System.out.println("AddToCart: Product with ID " + productID + " " +
                    "not found " +
                    "in  the database.");
        }
    }

    public void checkout() {
        /* If the checkout process is successful, reset the cart */
        //mention date shipped and date delivered
        PaymentDao paymentDao = new PaymentDao();
        paymentDao.processPayments(getUserID());
         icart.clearCart();
        // Log a successful checkout
        System.out.println("Checkout successful for shopper: " + getCustomerName());

    }


    public void removeFromCart(int productID) {
        Product product = productDAO.getProductById(productID);
        if (product != null) {
            boolean isProductRemoved =
                    icart.removeFromCart(productDAO.getProductById(productID));
            if (isProductRemoved) {
                System.out.println( product.getProductName() + " removed from" +
                        " cart for " +  getUserName());
            } else {
                System.out.println("RemoveFromCart: Product with ID " +
                        productID + " not " + "found in the cart.");
            }
        } else {
            System.out.println("RemoveFromCart: Product with ID " + productID +
                    " not found" +
                    " " +
                    "in  the database.");
        }
    }


    public void addToWishList(Product product) {
        wishList.add(product);
        System.out.println(product.getProductName() + " added to wishlist for " + getUserName());
    }

    public void removeFromWishList(Product product) {
        wishList.remove(product);
        System.out.println(product.getProductName() + " removed from wishlist for " + getUserName());
    }

    // Override the updateProfile method to include additional logic for the Shopping class
    @Override
    public void updateProfile() {
        super.updateProfile();
        System.out.println("Shopping profile updated for " + getUserName());
    }

    // Override the getOrderStatus method to include additional logic for the Shopping class
    @Override
    public String getOrderStatus() {
        // Additional logic specific to retrieving order status for a shopping customer
        return "Order status for shopping customer " + getUserName();
    }

    // Additional methods for managing order history
    public void addToOrderHistory(Order order) {
        orderHistory.add(order);
        System.out.println("Order added to history for " + getUserName());
    }

    public List<Order> getOrderHistory() {
        return orderHistory;
    }

    @Override
    public String toString() {
        return "Shopping{" +
                super.toString() +  // Using super.toString() to include
                // fields from the superclass
                ", wishList=" + wishList +
                ", orderHistory=" + orderHistory +
                ", cart=" + cart +
                '}';
    }

}
