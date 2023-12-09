package com.scm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//why i am not seeing run option?
import static com.scm.ProductDAO.readAllProducts;

@SpringBootApplication
public class ScmApplication {

    public static void main(String[] args) {
//        SpringApplication.run(ScmApplication.class, args);
        ElectronicProductFactory electronicProductFactory =
                new ElectronicProductFactory();
        Product electronicProduct =
                electronicProductFactory.createProduct( 3,  "MacBook Pro 2019",
                        "This laptop is mediocre.", 500, 2.5f);
        Product.addProductToDB(electronicProduct, Product.productMap);

        System.out.println(Product.productMap);
        readAllProducts();
    }
}

