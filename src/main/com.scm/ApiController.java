import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api")
public class ApiController {

    private final ApiService apiService; // Assume ApiService is a service class handling business logic

    // Constructor injection of ApiService
    public ApiController(ApiService apiService) {
        this.apiService = apiService;
    }

    // Endpoint to create a user
    @PostMapping("/users")
    public User createUser(@RequestBody UserDetails userDetails) {
        return apiService.createUser(userDetails);
    }

    // Endpoint to update a product
    @PutMapping("/products")
    public Product updateProduct(@RequestBody ProductDetails productDetails) {
        return apiService.updateProduct(productDetails);
    }

    // Endpoint to update stock
    @PutMapping("/stock")
    public Stock updateStock(@RequestBody StockDetails stockDetails) {
        return apiService.updateStock(stockDetails);
    }

    // Endpoint to login a user
    @PostMapping("/login")
    public User loginUser(@RequestBody UserDetails userDetails) {
        return apiService.loginUser(userDetails);
    }

    // Endpoint to create a report
    @PostMapping("/reports")
    public Report createReport() {
        return apiService.createReport();
    }

    // Endpoint to browse products
    @GetMapping("/products")
    public List<Product> browseProduct(@RequestParam(name = "productName", required = false) String productName) {
        return apiService.browseProducts(productName);
    }

    // Endpoint to sort products
    @GetMapping("/products/sort")
    public List<Product> sortProduct(@RequestParam(name = "sortBy", required = false) String sortBy) {
        return apiService.sortProducts(sortBy);
    }

    // Endpoint to search products
    @GetMapping("/products/search")
    public List<Product> searchProduct(@RequestParam(name = "query") String query) {
        return apiService.searchProducts(query);
    }

    // Other endpoints and methods as needed

    // Exception handling example
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception e) {
        return new ResponseEntity<>("An error occurred: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
