package com.scm;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
public class Order extends Customer {
    // Attributes
    private int orderID;
    private String orderStatus;
    private Date dateShipped;
    private List<OrderObserver> observers = new ArrayList<>();

    // Constants
    private static final String DEFAULT_ORDER_STATUS = "Pending";
    private OrderDAO orderDAO;

    // Constructors
    public Order(int orderID,
                 String shippingInfo, int userID, String userName,
                 String email, String password, Date registerDate,
                 String customerName, Address address) {
        super(userID, userName, email, password, registerDate,
                shippingInfo, customerName, address);
        this.orderID = orderID;
        this.orderDAO = OrderDAO.getInstance();
        this.orderStatus = DEFAULT_ORDER_STATUS;
        this.dateShipped = null;
    }

    // Methods

    public void changeOrderStatus(String newStatus) {
        this.orderStatus = newStatus;
        this.dateShipped =
                Date.from(LocalDate.now().plusDays(7).atStartOfDay(
                        ZoneId.systemDefault()).toInstant());
        System.out.println("Order status changed to " + newStatus + " on " +
                dateShipped);
        notifyObservers();
        this.orderDAO.updateOrderStatus(orderID, orderStatus, dateShipped);
    }


    public int getOrderID() {
        return orderID;
    }

    public Date getDateShipped() {
        return dateShipped;
    }

    // Methods for observer management
    public void addObserver(OrderObserver observer) {
        observers.add(observer);
    }

    public void removeObserver(OrderObserver observer) {
        observers.remove(observer);
    }

    @Override
    public String getOrderStatus() {
        // Additional logic specific to retrieving order status for a shopping customer
        return this.orderStatus;
    }
    // Notify observers when the order status changes
    private void notifyObservers() {
        for (OrderObserver observer : observers) {
            observer.update(this);
        }
    }

    @Override
    public String toString() {
        return "Order{" +
                super.toString() +  // Using super.toString() to include
                // fields from the superclass
                ", orderID= " + orderID +
                ", orderStatus=" + orderStatus +
                ", dateShipped=" + dateShipped +
                '}';
    }
}
