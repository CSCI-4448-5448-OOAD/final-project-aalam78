
package com.scm

import java.util.HashMap;

public class Order extends Customer{
    // Attributes
    private int orderID;
    private String status;
    private HashMap<Integer, Product> products;
    private String orderStatus;
    private String dateCreated;
    private String dateShipped;
    private float unitCost;
    private float subtotal;
    private Payment paymentInfo;
    private ShippingInfo shippingInfo;
    private static HashMap<Integer, Order> orderMap;

    // Constructors
    public Order(int orderID, String status, HashMap<Integer, Product> products, String dateCreated,
                 float unitCost, Payment paymentInfo, ShippingInfo shippingInfo) {
        this.orderID = orderID;
        this.status = status;
        this.products = products;
        this.orderStatus = "Pending"; // Default status
        this.dateCreated = dateCreated;
        this.dateShipped = null; // Not shipped initially
        this.unitCost = unitCost;
        this.subtotal = calculateSubtotal();
        this.paymentInfo = paymentInfo;
        this.shippingInfo = shippingInfo;
        if (orderMap == null) {
            orderMap = new HashMap<>();
        }
        orderMap.put(orderID, this);
    }

    // Methods
    public static Order getOrder(int orderID) {
        return orderMap.get(orderID);
    }

    public void changeOrderStatus(String newStatus) {
        this.orderStatus = newStatus;
    }

    public String getOrderStatus(int orderID) {
        return orderMap.get(orderID).orderStatus;
    }

    public int getOrderID() {
        return orderID;
    }

    public String getDateShipped() {
        return dateShipped;
    }

    public void addOrderToDB(Order order) {
        orderMap.put(order.getOrderID(), order);
    }

    public void removeOrderFromDB(Order order) {
        orderMap.remove(order.getOrderID());
    }

    private float calculateSubtotal() {
        // Logic to calculate subtotal based on product prices and quantities
        // You need to implement this based on your requirements
        return 0.0f; // Placeholder value
    }
}
