package com.scm

public class ShippingInfo {
    private int shippingID;
    private String shippingType;
    private float shippingCost;
    private int shippingRegionId;
    private Address address;

    // Constructor
    public ShippingInfo(int shippingID, String shippingType, float shippingCost, int shippingRegionId, Address address) {
        this.shippingID = shippingID;
        this.shippingType = shippingType;
        this.shippingCost = shippingCost;
        this.shippingRegionId = shippingRegionId;
        this.address = address;
    }

    // Methods
    public void updateShippingInfo() {
        // Logic to update shipping information
        System.out.println("Shipping information updated for Shipping ID: " + shippingID);
    }

    public String getShippingStatus() {
        // Logic to retrieve shipping status
        return "Shipping status for Shipping ID: " + shippingID;
    }

    public float getShippingCost() {
        return shippingCost;
    }

    public String getShippingType() {
        return shippingType;
    }

    public int getShippingID() {
        return shippingID;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address newAddress) {
        this.address = newAddress;
        System.out.println("Shipping address updated for Shipping ID: " + shippingID);
    }