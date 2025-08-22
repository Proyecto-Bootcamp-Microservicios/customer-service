package com.NTTDATA.bootcamp.msvc_customer.infrastructure.persistence.entity;

import com.NTTDATA.bootcamp.msvc_customer.domain.enums.CustomerType;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Document(collection = "customers")
@Getter
@Setter
public class PersonalCollection extends CustomerCollection {

    private String firstName;
    private String lastName;
    private LocalDate birthDate;

    public PersonalCollection() {
        super(CustomerType.PERSONAL.name());
    }

    public PersonalCollection(String id, String name, String documentType, String documentNumber, String email, String phone, String street, String city, String country, String zipCode, boolean active, LocalDateTime createdAt, LocalDateTime updatedAt, String firstName, String lastName, LocalDate birthDate) {
        super(id, name, documentType, documentNumber, email, phone, street, city, country, zipCode, active, createdAt, updatedAt, CustomerType.PERSONAL.name());
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
    }
}
