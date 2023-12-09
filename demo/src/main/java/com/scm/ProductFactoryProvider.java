package com.scm;

public class ProductFactoryProvider {
    // Factory class to abstract the creation of product factories
        public static AbstractProductFactory createFactory(String productType) {
            if ("Electronic".equalsIgnoreCase(productType)) {
                return new ElectronicProductFactory();
            } else if ("Furniture".equalsIgnoreCase(productType)) {
                return new FurnitureProductFactory();
            } else {
                throw new IllegalArgumentException("Invalid product type");
            }
        }

    // Example usage
    String productType = "Electronic"; // or "Furniture"
    AbstractProductFactory productFactory = ProductFactoryProvider.createFactory(productType);

// Now you have the productFactory without explicitly dealing with the if-else logic

}
