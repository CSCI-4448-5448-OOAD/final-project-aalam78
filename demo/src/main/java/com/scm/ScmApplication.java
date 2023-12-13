package com.scm;

import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ScmApplication {

    public static void main(String[] args) {
//        SpringApplication.run(ScmApplication.class, args);
        String productType = "Electronic"; // or "Furniture"
        AbstractProductFactory factory =
                ProductFactoryProvider.createFactory(productType);
        Product product =
                factory.createProduct("MacBook Pro 2019",
                        "This laptop is mediocre.", 500, 2.5f);

        ProductDAO productDAO = new ProductDAO();
        productDAO.addProductToDB(product, Product.productMap);

        productType = "Furniture"; // or "Furniture"
        factory = ProductFactoryProvider.createFactory(productType);
        product = factory.createProduct("Sofa",
                        "Amazing footon sofa.", 200, 21f);

        productDAO.addProductToDB(product, Product.productMap);
        productDAO.readAllProducts();

        CustomerDAO customerDAO = new CustomerDAO();
        Shopping shopper = customerDAO.getShopperById(1);
        System.out.println(shopper);
        shopper.addToCart(1);
        shopper.addToCart(2);

        OrderDAO orderDAO = new OrderDAO();
        orderDAO.clearPreviousEntries(shopper.getUserID());
        // if i receive payment than only i will consider the order as placed
        // if payment is not done, than order is not placed
        // Clear previous entries for the user in order_table and order_item

        // check if the order is placed
        Cart cart = shopper.getICart();
        // show cart items
        System.out.println("Cart Items:");
        // check if the cart is empty
        cart.viewCart();
        cart.readCartItems();

        int orderID = shopper.checkout();
        System.out.println("Order ID: " + orderID);
        // implement observer pattern here for order class
        // update the order status
        Order order = new Order(orderID, shopper.getShippingInfo(),
                shopper.getUserID(),
                shopper.getUserName(), shopper.getUserEmail(), "",
                shopper.getRegisterDate(), shopper.getCustomerName(),
                shopper.getAddress());
        OrderStatusObserver observer = new OrderStatusObserver();
        order.addObserver(observer);
        order.changeOrderStatus("Shipped");


        // Implement logic for the checkout process
        // checkout the cart

        // check if the cart is empty
        cart.viewCart();
        cart.readCartItems();
        // check if the order is placed
        orderDAO.readAllOrders();

        // implementing observer pattern to track order status



    }
}
