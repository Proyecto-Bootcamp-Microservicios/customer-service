package com.NTTDATA.bootcamp.msvc_customer.domain.vo;

import lombok.Getter;

@Getter
public final class Address {
    private final String street;
    private final String city;
    private final String country;
    private final String zipCode;

    private Address(String street, String city, String country, String zipCode) {
        if(street == null || street.trim().isEmpty()) throw new IllegalArgumentException("Street cannot be null or empty");
        if(city == null || city.trim().isEmpty()) throw new IllegalArgumentException("City cannot be null or empty");
        if(country == null || country.trim().isEmpty()) throw new IllegalArgumentException("Country cannot be null or empty");
        if(zipCode == null || zipCode.trim().isEmpty()) throw new IllegalArgumentException("Zip code cannot be null or empty");
        this.street = street;
        this.city = city;
        this.country = country;
        this.zipCode = zipCode;
    }

    public static Address of(String street, String city, String country, String zipCode) {
        return new Address(street, city, country, zipCode);
    }

}
