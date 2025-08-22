package com.NTTDATA.bootcamp.msvc_customer.domain;

import com.NTTDATA.bootcamp.msvc_customer.domain.enums.CustomerType;
import com.NTTDATA.bootcamp.msvc_customer.domain.enums.DocumentType;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
public final class Enterprise extends Customer {

    private final String businessName;

    private Enterprise(String id, String businessName,
                       String documentNumber,
                       String email, String phone,
                       String street, String city, String country, String zipCode,
                       Audit audit, boolean active) {
        super(id, businessName, documentNumber, DocumentType.RUC,
                email, phone,
                street, city, country, zipCode,
                audit, active);
        this.businessName = businessName;
        validateBusinessRules();
    }

    public static Enterprise create(String businessName,
                                    String documentNumber,
                                    String email, String phone,
                                    String street, String city, String country, String zipCode) {
        return new Enterprise(UUID.randomUUID().toString(), businessName,
                documentNumber,
                email, phone,
                street, city, country, zipCode,
                Audit.create(), true);
    }

    public static Enterprise reconstruct(String id, String businessName,
                                         String documentNumber,
                                         String email, String phone,
                                         String street, String city, String country, String zipCode,
                                         LocalDateTime createdAt, LocalDateTime updatedAt, boolean active) {
        return new Enterprise(id, businessName,
                documentNumber,
                email, phone,
                street, city, country, zipCode,
                Audit.reconstruct(createdAt, updatedAt), active);
    }

    public Enterprise desactivate() {
        return new Enterprise(this.id.getValue(), this.businessName,
                this.getDocumentValue(),
                this.getEmail(), this.getPhone(),
                this.getStreet(), this.getCity(), this.getCountry(), this.getZipCode(),
                this.audit.update(), false);
    }

    public Enterprise update(String businessName, String email, String phone, String street, String city, String country, String zipCode) {
        return new Enterprise(this.id.getValue(), businessName,
                this.getDocumentValue(),
                email, phone,
                street, city, country, zipCode,
                this.audit.update(), this.isActive());
    }

    @Override
    public String getFullName() {
        return businessName;
    }

    @Override
    public boolean canHaveASavingsAccount() {
        return false;
    }

    @Override
    public boolean canHaveMultipleFixedTermAccount() {
        return false;
    }

    @Override
    public boolean canHaveMultipleCurrentAccounts() {
        return true;
    }

    @Override
    public boolean canHaveMultipleCredits() {
        return true;
    }

    @Override
    public void validateBusinessRules() {
        if(documentNumber.getType() != DocumentType.RUC) throw new IllegalArgumentException("Enterprise customer must have RUC document type");
        if(businessName == null || businessName.trim().isEmpty()) throw new IllegalArgumentException("Business name is required for enterprise customer");
    }

    @Override
    public CustomerType getCustomerType() {
        return CustomerType.ENTERPRISE;
    }

}
