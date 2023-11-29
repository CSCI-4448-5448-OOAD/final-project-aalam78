// Abstract Factory interface
interface AbstractProductFactory {
    Product createElectronicProduct(int productID, String name, String description, float price, float productWeight);

    Product createFurnitureProduct(int productID, String name, String description, float price, float productWeight);
}