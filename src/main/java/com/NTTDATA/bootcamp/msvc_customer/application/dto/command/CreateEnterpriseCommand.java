package com.NTTDATA.bootcamp.msvc_customer.application.dto.command;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public final class CreateEnterpriseCommand {
    private final String businessName;
    private final String ruc;
    private final String email;
    private final String phone;
    private final String street;
    private final String city;
    private final String country;
    private final String zipCode;
}
