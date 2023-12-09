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
        productDAO.readAllProducts();

        CustomerDAO customerDAO = new CustomerDAO();
        Shopping shopper = customerDAO.getShopperById(1);
        System.out.println(shopper);
        shopper.addToCart(1);

        OrderDAO orderDAO = new OrderDAO();
        orderDAO.placeOrder(shopper);

        // Update the order status in the database so that the observers are
        // notified
    }
}
