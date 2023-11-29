// Concrete ElectronicProductFactory class
class ElectronicProductFactory implements AbstractProductFactory {
    @Override
    public Product createElectronicProduct(int productID, String name, String description, float price, float productWeight) {
        return new ElectronicProduct(productID, name, description, price, productWeight);
    }
}