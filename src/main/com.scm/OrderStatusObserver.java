package com.scm
// Concrete observer class
class OrderStatusObserver implements OrderObserver {
    @Override
    public void update(Order order) {
        System.out.println("Order " + order.getOrderID() + " has been updated. New status: " + order.getOrderStatus());
        @Override
        public void update(Order order) {
            System.out.println("Order " + order.getOrderID() + " has been updated. New status: " + order.getOrderStatus());
        }
    }

