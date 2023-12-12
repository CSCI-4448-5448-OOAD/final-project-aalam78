package com.scm;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.scm.Product;
import com.scm.Report.ReportService;
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

    @PostMapping("/login")
    public ResponseEntity<User> loginUser(@RequestBody User userDetails) {
        // Assuming you have a service class to handle user authentication
        // You might want to inject a UserService or AuthenticationService
        // and call a method like authenticateUser(userDetails) in the service

        boolean isAuthenticated = authenticateUser(userDetails);

        if (isAuthenticated) {
            // If authentication is successful, return the logged-in user
            return ResponseEntity.ok(userDetails);
        } else {
            // If authentication fails, return an unauthorized status
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

        // Example authentication method (replace it with your actual logic)
        private boolean authenticateUser(User userDetails) {
            // Your authentication logic goes here
            // This is just a placeholder, replace it with your actual authentication logic

            // For example, you might check against a database or use Spring Security
            // In this example, we'll assume a hardcoded username and password for simplicity

            String expectedUsername = "admin";
            String expectedPassword = "password";

            return expectedUsername.equals(userDetails.getUserName()) &&
                expectedPassword.equals(userDetails.getPassword());
        }

    



    

        @PostMapping("/reports")
public ResponseEntity<Report> createReport(@RequestBody ReportService reportDetails) {
    try {
        // Pass the required parameters for Report instantiation
        Report report = new Report(0, null, 0/* userID, reportType, reportID */);
    
        // Create ReportService using the specific instance of Report
        ReportService reportService = report.new ReportService();
    
        // You may need to map ReportDetails to Report
        // For simplicity, let's assume you have a method like mapToReport in ReportService
        Report mappedReport = reportService.mapToReport(reportDetails);
    
        // Generate the report
        reportService.generateReport(mappedReport);
    
        // Return the created report in the response body
        return ResponseEntity.ok(mappedReport);
    } catch (Exception e) {
        // Log the exception
        e.printStackTrace();
    
        // Return an error response
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
}


    @DeleteMapping("/exit")
    public ResponseEntity<Void> exit() {
        // Implementation logic for application exit
        // ...

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/products")
    public ResponseEntity<List<Product>> browseProduct() {
        try {
            // Assuming you have a ProductService to handle product-related operations
            List<Product> productList = Product.getAllProducts();

            // Check if the product list is not empty
            if (!productList.isEmpty()) {
                // Return the list of products in the response body
                return ResponseEntity.ok(productList);
            } else {
                // If the list is empty, return a not found response
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            // Log the exception
            e.printStackTrace();

            // Return an error response
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    

    // Similar implementations for other endpoints (sort, search, getUser, getStock, getOrder, getProduct)
}
