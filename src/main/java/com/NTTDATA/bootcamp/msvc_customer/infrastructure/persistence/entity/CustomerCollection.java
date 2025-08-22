package com.NTTDATA.bootcamp.msvc_customer.infrastructure.persistence.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document("customers")
@Getter
@Setter
public abstract class CustomerCollection {

    @Id
    private String id;
    private String name;
    private String documentType;

    @Indexed(unique = true)
    private String documentNumber;

    @Indexed(unique = true)
    private String email;
    private String phone;
    private String street;
    private String city;
    private String country;
    private String zipCode;
    private boolean active;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @Indexed
    private String customerType;

    protected CustomerCollection(String customerType) {
        this.customerType = customerType;
    }

    protected CustomerCollection(String id, String name, String documentType, String documentNumber, String email, String phone, String street, String city, String country, String zipCode, boolean active, LocalDateTime createdAt, LocalDateTime updatedAt, String customerType) {
        this(customerType);
        this.id = id;
        this.name = name;
        this.documentType = documentType;
        this.documentNumber = documentNumber;
        this.email = email;
        this.phone = phone;
        this.street = street;
        this.city = city;
        this.country = country;
        this.zipCode = zipCode;
        this.active = active;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

}
