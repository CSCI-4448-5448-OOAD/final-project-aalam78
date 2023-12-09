package com.scm;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
public class Order extends Customer {
    // Attributes
    private int orderID;
    private String status;
    private HashMap<Integer, Product> products;
    private String orderStatus;
    private String dateCreated;
    private String dateShipped;
    private float unitCost;
    private float subtotal;
    private Payment paymentType;
    private ShippingInfo shippingInfo;
    private static HashMap<Integer, Order> orderMap;
    private List<OrderObserver> observers = new ArrayList<>();

    // Constants
    private static final String DEFAULT_ORDER_STATUS = "Pending";


    // Constructors
    public Order(int orderID, String status,
                 HashMap<Integer, Product> products,  String dateCreated,
                 float unitCost, Payment paymentType,
                 ShippingInfo shippingInfo, int userID, String userName,
                 String email, String password, Date registerDate,
                 String customerName, Address address) {
        super(userID, userName, email, password, registerDate, String.valueOf(shippingInfo), customerName, address);
        this.orderID = orderID;
        this.status = status;
        this.products = products;
        this.orderStatus = DEFAULT_ORDER_STATUS;
        this.dateCreated = dateCreated;
        this.dateShipped = null;
        this.unitCost = unitCost;
        this.subtotal = calculateSubtotal();
        this.paymentType = paymentType;
        this.shippingInfo = shippingInfo;
        orderMap.put(orderID, this);
    }

    // Methods
    public static Order getOrder(int orderID) {
        return orderMap.get(orderID);
    }

    public void changeOrderStatus(String newStatus) {
        this.orderStatus = newStatus;
        notifyObservers();
    }

    public String getOrderStatus(int orderID) {
        Order order = orderMap.get(orderID);
        if (order != null) {
            return order.orderStatus;
        } else {
            return "Order not found";
        }
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

    // Methods for observer management
    public void addObserver(OrderObserver observer) {
        observers.add(observer);
    }

    public void removeObserver(OrderObserver observer) {
        observers.remove(observer);
    }

    // Notify observers when the order status changes
    private void notifyObservers() {
        for (OrderObserver observer : observers) {
            observer.update(this);
        }
    }
}
