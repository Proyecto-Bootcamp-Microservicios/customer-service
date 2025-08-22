package com.NTTDATA.bootcamp.msvc_customer.infrastructure.persistence.entity;

import com.NTTDATA.bootcamp.msvc_customer.domain.enums.CustomerType;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "customers")
@Getter
@Setter
public class EnterpriseCollection extends CustomerCollection {

    private String businessName;

    protected EnterpriseCollection() {
        super(CustomerType.ENTERPRISE.name());
    }

    public EnterpriseCollection(String id, String name, String documentType, String documentNumber, String email, String phone, String street, String city, String country, String zipCode, boolean active, LocalDateTime createdAt, LocalDateTime updatedAt, String businessName) {
        super(id, name, documentType, documentNumber, email, phone, street, city, country, zipCode, active, createdAt, updatedAt, CustomerType.ENTERPRISE.name());
        this.businessName = businessName;
    }
}
