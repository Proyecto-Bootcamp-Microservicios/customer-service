package com.NTTDATA.bootcamp.msvc_customer.domain;

import com.NTTDATA.bootcamp.msvc_customer.domain.enums.CustomerType;
import com.NTTDATA.bootcamp.msvc_customer.domain.enums.DocumentType;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
public final class Personal extends Customer {
    private final String firstName;
    private final String lastName;
    private final LocalDate birthDate;

    public static final int MIN_AGE = 18;
    public static final int MAX_AGE = 100;

    private Personal(String id, String firstName, String lastName,
                     String documentNumber, DocumentType documentType,
                     String email, String phone,
                     String street, String city, String country, String zipCode,
                     LocalDate birthDate, Audit audit, boolean active) {
        super(id, firstName + " " + lastName,
                documentNumber, documentType,
                email, phone,
                street, city, country, zipCode,
                audit, active);
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        System.out.println(this.firstName + " " + this.lastName);
        validateBusinessRules();
    }

    public static Personal create(String firstName, String lastName,
                                  String documentNumber, DocumentType documentType,
                                  String email, String phone,
                                  String street, String city, String country, String zipCode,
                                  LocalDate birthDate) {
        return new Personal(UUID.randomUUID().toString(), firstName, lastName,
                documentNumber, documentType,
                email, phone,
                street, city, country, zipCode,
                birthDate, Audit.create(), true);
    }

    public Personal update(String firstName, String lastName, String email, String phone, String street, String city, String country, String zipCode){
        return new Personal(this.id.getValue(), firstName, lastName, this.getDocumentValue(), this.documentNumber.getType(), email, phone, street, city, country, zipCode, this.birthDate, this.getAudit().update(), this.isActive());
    }

    public static Personal reconstruct(String id, String firstName, String lastName,
                                       String documentNumber, DocumentType documentType,
                                       String email, String phone,
                                       String street, String city, String country, String zipCode,
                                       LocalDate birthDate, LocalDateTime createdAt, LocalDateTime updatedAt,
                                       boolean active) {
        return new Personal(id, firstName, lastName,
                documentNumber, documentType,
                email, phone,
                street, city, country, zipCode,
                birthDate, Audit.reconstruct(createdAt, updatedAt), active);
    }

    @Override
    public String getFullName() {
        return firstName + " " + lastName;
    }

    @Override
    public boolean canHaveASavingsAccount() {
        return true;
    }

    @Override
    public boolean canHaveMultipleFixedTermAccount() {
        return true;
    }

    @Override
    public boolean canHaveMultipleCurrentAccounts() {
        return false;
    }

    @Override
    public boolean canHaveMultipleCredits() {
        return false;
    }

    @Override
    public void validateBusinessRules() {
        if (this.firstName == null || this.firstName.trim().isEmpty()) throw new IllegalArgumentException("First name is required for personal customer");
        if (this.lastName == null || this.lastName.trim().isEmpty()) throw new IllegalArgumentException("Last name is required for personal customer");
        if (this.documentNumber.getType() == DocumentType.RUC) throw new IllegalArgumentException("Personal customer cannot have RUC");
        if (this.birthDate == null) throw new IllegalArgumentException("Birth date is required for personal customer");
        if (LocalDate.now().getYear() - this.birthDate.getYear() < 18) throw new IllegalArgumentException("The minimum age is 18 years");
    }

    @Override
    public CustomerType getCustomerType() {
        return CustomerType.PERSONAL;
    }

    public Personal desactivate() {
        return new Personal(this.getId().getValue(), this.firstName, this.lastName, this.documentNumber.getValue(), this.documentNumber.getType(), this.getEmail(), this.getPhone(), this.getStreet(), this.getCity(), this.getCountry(), this.getZipCode(), this.birthDate, this.audit.update(), false);
    }
}
