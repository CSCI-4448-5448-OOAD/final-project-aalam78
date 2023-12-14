package com.scm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ScmApplication {

    public static void main(String[] args) {
        SpringApplication.run(ScmApplication.class, args);

//        ProductDAO productDAO = ProductDAO.getInstance();
//        productDAO.clearPreviousEntries();
//
//        String productType = "Electronic"; // or "Furniture"
//        AbstractProductFactory factory =
//                ProductFactoryProvider.createFactory(productType);
//        Product product =
//                factory.createProduct("MacBook Pro 2019",
//                        "This laptop is mediocre.", 500, 2.5f);
//
//
//        productDAO.addProductToDB(product, Product.productMap);
//
//        productType = "Furniture"; // or "Furniture"
//        factory = ProductFactoryProvider.createFactory(productType);
//        product = factory.createProduct("Sofa",
//                        "Amazing footon sofa.", 200, 21f);
//
//        productDAO.addProductToDB(product, Product.productMap);
//        productDAO.readAllProducts();
//
//        CustomerDAO customerDAO = new CustomerDAO();
//        Shopping shopper = customerDAO.getShopperById(1);
//        System.out.println(shopper);
//        shopper.addToCart(1);
//        shopper.addToCart(2);
//
//        shopper.viewCart();
//
//        OrderDAO orderDAO = OrderDAO.getInstance();
//        orderDAO.clearPreviousEntries(shopper.getUserID());
//        // if i receive payment than only i will consider the order as placed
//        // if payment is not done, than order is not placed
//        // Clear previous entries for the user in order_table and order_item
//
//
//
//        int orderID = shopper.checkout();
//        System.out.println("Order ID: " + orderID);
//        // implement observer pattern here for order class
//        // update the order status
//        if (orderID != -1) {
//            Order order = new Order(orderID, shopper.getShippingInfo(),
//                    shopper.getUserID(),
//                    shopper.getUserName(), shopper.getUserEmail(), "",
//                    shopper.getRegisterDate(), shopper.getCustomerName(),
//                    shopper.getAddress());
//            OrderStatusObserver observer = new OrderStatusObserver();
//            order.addObserver(observer);
//            order.changeOrderStatus("Shipped");
//        }
//
//        shopper.viewCart();
//
//        // check if the order is placed
//        orderDAO.readAllOrders();
    }
}
