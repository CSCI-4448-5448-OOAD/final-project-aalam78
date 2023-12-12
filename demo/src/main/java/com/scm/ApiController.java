package com.scm;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ApiController {

    // No Model and MainView fields

    // Constructor removed for brevity

    @PostMapping("/users")
    public ResponseEntity<User> createUser(@RequestBody User userDetails) {
        try {
            // Validate user details and create a new user
            User newUser = new User(0, null, null, null, null);
            newUser.setUserName(userDetails.getUserName());
            // set other user details...

            // Add the new user to the database
            User.addUserToDB(newUser);

            // Return the created user in the response body
            return ResponseEntity.ok(newUser);
        } catch (Exception e) {
            // Log the exception
            e.printStackTrace();

            // Return an error response
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    
    @PutMapping("/products")
    public ResponseEntity<Product> updateProduct(@RequestBody Product productDetails) {
    try {
        // Retrieve the existing product from the database or data store
        Product existingProduct = Product.getProductFromDB(productDetails.getProductID(), Product.productMap);

        if (existingProduct == null) {
            // Product not found, return a not found response
            return ResponseEntity.notFound().build();
        }

        // Update the existing product with the details from the request body
        // Assuming productDetails.getProductID() returns an int
        existingProduct.setProductID(productDetails.getProductID());
        existingProduct.setProductDescription(productDetails.getProductDescription());
        existingProduct.setProductPrice(productDetails.getProductPrice());
        existingProduct.setProductWeight(productDetails.getProductWeight());

        // Save the updated product back to the database or data store
        Product.productMap.put(existingProduct.getProductID(), existingProduct);

        // Return the updated product in the response body
        return ResponseEntity.ok(existingProduct);
        } catch (Exception e) {
            // Log the exception
            e.printStackTrace();

            // Return an error response
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }


    @PutMapping("/stocks")
    public ResponseEntity<Stock> updateStock(@RequestBody Stock stockDetails) {
    try {
        // Retrieve the existing stock from the database or data store
        Stock existingStock = Stock.getStockFromDB(stockDetails.getAvailableProducts().get(0).getProductID());

        if (existingStock == null) {
            // Stock not found, return a not found response
            return ResponseEntity.notFound().build();
        }

        // Update the existing stock with the details from the request body
        existingStock.setAvailableProducts(stockDetails.getAvailableProducts());
        existingStock.setAmount(stockDetails.getAmount());
        existingStock.setAmountOfProduct(stockDetails.getAmountOfProduct());

        // Save the updated stock back to the database or data store
        Stock.stockMap.put(existingStock.getAvailableProducts().get(0).getProductID(), existingStock);

        // Return the updated stock in the response body
            return ResponseEntity.ok(existingStock);
        } catch (Exception e) {
            // Log the exception
            e.printStackTrace();

            // Return an error response
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    /* 


    @PostMapping("/login")
    public ResponseEntity<User> loginUser(@RequestBody User userDetails) {
        // Implementation logic for user login
        // ...

        return ResponseEntity.ok(); Logged-in user 
    }

    @PostMapping("/reports")
    public ResponseEntity<Report> createReport() {
        // Implementation logic to generate a report
        // ...

        return ResponseEntity.ok(); //Created report
    }

    @DeleteMapping("/exit")
    public ResponseEntity<Void> exit() {
        // Implementation logic for application exit
        // ...

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/products")
    public ResponseEntity<List<Product>> browseProduct() {
        // Implementation logic to retrieve and return a list of products
        // ...

        return ResponseEntity.ok(); //List of products 
    }
    
    */

    // Similar implementations for other endpoints (sort, search, getUser, getStock, getOrder, getProduct)
}
