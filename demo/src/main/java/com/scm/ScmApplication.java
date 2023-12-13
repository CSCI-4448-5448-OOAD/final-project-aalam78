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


        orderDAO.placeOrder(shopper);

        // check if the order is placed
        Cart cart = shopper.getICart();
        // show cart items
        System.out.println("Cart Items:");
        // check if the cart is empty
        cart.viewCart();
        cart.readCartItems();

       // implement observer pattern here for order class
        // update the order status
        // Implement logic for the checkout process
        // checkout the cart
        shopper.checkout();

        // check if the cart is empty
        cart.viewCart();
        cart.readCartItems();
        // check if the order is placed
        orderDAO.readAllOrders();

       // orderDAO.updateOrderStatusAndDateShipped(1, "Shipped", new Date());
       // orderDAO.readAllOrders();



        //update total quantity in order_item table


/*
        // remove the product from the database
        ProductDAO.removeProductFromDB(product, Product.productMap);
        // check if the product is removed from the database
        productDAO.readAllProducts();
        //method to read cart items







        // observer pattern implementation to tracks the order status]
        // Create an order observer
/*
        // Create an observer
        OrderObserver observer = new OrderStatusObserver();

        // Register the observer with the order
        addObserver(observer);

        // Perform some actions that change the order status
        order.changeOrderStatus("Processing");

        // Add more observers if needed
        // OrderObserver anotherObserver = new AnotherOrderObserver();
        // order.addObserver(anotherObserver);

        // Perform more actions that change the order status
        order.changeOrderStatus("Shipped");

        // Implement the observer pattern to track the order status


    /*    // Check if the product is removed from the order
        orderDAO.readAllOrders();

        // Update the product price
        productDAO.updateProductPrice(1, 2000);
        // Check if the product price is updated
        productDAO.readAllProducts();

        // Update the order status
        orderDAO.updateOrderStatus(1, "Delivered");
        // Check if the order status is updated
        orderDAO.readAllOrders();
    }
} */
        // Update the order status in the database so that the observers are
        // notified
        // add observer pattern implementation here

    }
}
