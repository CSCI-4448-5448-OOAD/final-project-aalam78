package com.scm;

import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ScmApplication {

    public static void main(String[] args) {
        System.out.println("No way");



     //SpringApplication.run(ScmApplication.class, args);
        String productType = "Electronic"; // or "Furniture"
        AbstractProductFactory factory =
                ProductFactoryProvider.createFactory(productType);
        Product product =
                factory.createProduct("MacBook Pro 2019",
                        "This laptop is mediocre.", 500, 2.5f);



        /* 
        ProductDAO productDAO = new ProductDAO();
        productDAO.addProductToDB(product, Product.productMap);

        productType = "Furniture"; // or "Furniture"
        factory = com.scm.ProductFactoryProvider.createFactory(productType);
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
        orderDAO.placeOrder(shopper);
        // remove the product from the database
        
        
        ProductDAO.removeProductFromDB(product, Product.productMap);
        // check if the product is removed from the database
        productDAO.readAllProducts();
        //method to read cart items
        shopper.getICart().Cart.readCartItems();
        // check if the product is removed from the shopping cart of the shopper
        shopper.getICart().Cart.readCartItems();

        // Check if the product is removed from the order
        orderDAO.readAllOrders();

        // Update the product price
        productDAO.updateProductPrice(1, 2000);
        // Check if the product price is updated
        productDAO.readAllProducts();

        // Update the order status
        orderDAO.updateOrderStatus(1, "Delivered");
        // Check if the order status is updated
        orderDAO.readAllOrders();

        /* */
    }
}
        // Update the order status in the database so that the observers are
        // notified
        // add observer pattern implementation here

    //}
//}
