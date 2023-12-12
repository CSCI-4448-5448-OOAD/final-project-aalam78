package com.scm;

public class Address {
    private String country;
    private String city;
    private String street;
    private String apartment;
    private String zipcode;

    // Constructor
    public Address(String country, String city, String street, String apartment, String zipcode) {
        this.country = country;
        this.city = city;
        this.street = street;
        this.apartment = apartment;
        this.zipcode = zipcode;
    }

    // Getter methods
    public String getCountry() {
        return country;
    }

    public String getCity() {
        return city;
    }

    public String getStreet() {
        return street;
    }

    public String getApartment() {
        return apartment;
    }

    public String getZipcode() {
        return zipcode;
    }

    // Override toString for a meaningful representation
    @Override
    public String toString() {
        return "Address{" +
                "country='" + country + '\'' +
                ", city='" + city + '\'' +
                ", street='" + street + '\'' +
                ", apartment='" + apartment + '\'' +
                ", zipcode='" + zipcode + '\'' +
                '}';
    }
}