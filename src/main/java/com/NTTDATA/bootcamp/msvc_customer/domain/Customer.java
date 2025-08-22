package com.NTTDATA.bootcamp.msvc_customer.domain;

import com.NTTDATA.bootcamp.msvc_customer.domain.enums.CustomerType;
import com.NTTDATA.bootcamp.msvc_customer.domain.enums.DocumentType;
import com.NTTDATA.bootcamp.msvc_customer.domain.vo.Address;
import com.NTTDATA.bootcamp.msvc_customer.domain.vo.ContactInfo;
import com.NTTDATA.bootcamp.msvc_customer.domain.vo.CustomerId;
import com.NTTDATA.bootcamp.msvc_customer.domain.vo.DocumentNumber;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public abstract class Customer {

    protected final CustomerId id;
    protected final String name;
    protected final DocumentNumber documentNumber;
    protected final ContactInfo contactInfo;
    protected final Audit audit;
    protected final boolean active;

    protected Customer(String id, String name,
                       String documentNumber, DocumentType documentType,
                       String email, String phone,
                       String street, String city, String country, String zipCode,
                       Audit audit, boolean active) {
        this.id = new CustomerId(id);
        this.name = name;
        this.documentNumber = new DocumentNumber(documentNumber, documentType);
        Address address = new Address(street, city, country, zipCode);
        this.contactInfo = new ContactInfo(email, phone, address);
        this.audit = audit;
        this.active = active;
    }

    public abstract String getFullName();
    public abstract boolean canHaveASavingsAccount();
    public abstract boolean canHaveMultipleFixedTermAccount();
    public abstract boolean canHaveMultipleCurrentAccounts();
    public abstract boolean canHaveMultipleCredits();
    public abstract void validateBusinessRules();
    public abstract CustomerType getCustomerType();

    public String getCustomerTypeString() {
        return getCustomerType().name();
    }

    public String getDocumentValue() {
        return documentNumber.getValue();
    }

    public String getDocumentType() {
        return documentNumber.getType().name();
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
