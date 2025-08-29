package com.NTTDATA.bootcamp.msvc_customer.domain;

import com.NTTDATA.bootcamp.msvc_customer.domain.enums.CustomerType;
import com.NTTDATA.bootcamp.msvc_customer.domain.enums.DocumentType;
import com.NTTDATA.bootcamp.msvc_customer.domain.vo.*;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public abstract class Customer {

    protected final CustomerId id;
    protected final String name;
    protected final IdentificationDocument identificationDocument;
    protected final ContactInfo contactInfo;
    protected final Audit audit;
    protected final boolean active;

    protected Customer(String id, String name,
                       String documentNumber, DocumentType documentType,
                       String email, String phone,
                       String street, String city, String country, String zipCode,
                       Audit audit, boolean active) {
        this.id = CustomerId.of(id);
        this.name = name;
        this.identificationDocument = IdentificationDocument.of(documentNumber, documentType);
        Address address = Address.of(street, city, country, zipCode);
        this.contactInfo = ContactInfo.of(email, phone, address);
        this.audit = audit;
        this.active = active;
        validateBusinessRules();
    }

    public abstract String getFullName();
    public abstract void validateBusinessRules();
    public abstract CustomerType getCustomerType();

    public String getCustomerTypeString() {
        return getCustomerType().name();
    }

    public String getDocumentValue() {
        return identificationDocument.getValue();
    }

    public String getDocumentType() {
        return identificationDocument.getType().name();
    }

    public String getEmail() {
        return contactInfo.getEmail();
    }

    public String getPhone() {
        return contactInfo.getPhone();
    }

    public String getStreet() {
        return contactInfo.getAddress().getStreet();
    }

    public String getCity() {
        return contactInfo.getAddress().getCity();
    }

    public String getCountry() {
        return contactInfo.getAddress().getCountry();
    }

    public String getZipCode() {
        return contactInfo.getAddress().getZipCode();
    }

    public LocalDateTime getCreatedAt() {
        return audit.getCreatedAt();
    }

    public LocalDateTime getUpdatedAt() {
        return audit.getUpdatedAt();
    }

}
