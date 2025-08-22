package com.NTTDATA.bootcamp.msvc_customer.application.dto.command;

import com.NTTDATA.bootcamp.msvc_customer.domain.enums.DocumentType;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
public final class CreatePersonalCommand {
    private final String firstName;
    private final String lastName;
    private final String documentNumber;
    private final DocumentType documentType;
    private final String email;
    private final String phone;
    private final String street;
    private final String city;
    private final String country;
    private final String zipCode;
    private final LocalDate birthDate;
}
