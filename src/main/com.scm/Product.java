import java.util.HashMap;

public class Product {
    private int productID;
    private String name;
    private String description;
    private float price;
    private float productWeight;

    // Constructor
    public Product(int productID, String name, String description, float price, float productWeight) {
        this.productID = productID;
        this.name = name;
        this.description = description;
        this.price = price;
        this.productWeight = productWeight;
    }

    // Getter and setter methods
    public void setProductName(String name) {
        this.name = name;
    }

    public String getProductName() {
        return name;
    }

    public void setProductWeight(float productWeight) {
        this.productWeight = productWeight;
    }

    public float getProductWeight() {
        return productWeight;
    }

    public int getProductID() {
        return productID;
    }

    public float getProductPrice() {
        return price;
    }

    public void setProductPrice(float price) {
        this.price = price;
    }

    // Method to check if the price is down and trigger an alert
    public boolean priceIsDownAlert(float newPrice) {
        return newPrice < price;
    }

    // Java doesn't support operator overloading, so I've created a separate method for adding to the database
    public static void addProductToDB(Product product, HashMap<Integer, Product> productMap) {
        productMap.put(product.getProductID(), product);
    }

    public static void removeProductFromDB(Product product, HashMap<Integer, Product> productMap) {
        productMap.remove(product.getProductID());
    }

    // Method to get a product from the database based on productID
    public static Product getProductFromDB(int productID, HashMap<Integer, Product> productMap) {
        return productMap.get(productID);
    }