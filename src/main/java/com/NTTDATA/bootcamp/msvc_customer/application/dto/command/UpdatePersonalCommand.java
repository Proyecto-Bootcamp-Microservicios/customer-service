package com.NTTDATA.bootcamp.msvc_customer.application.dto.command;

import lombok.AllArgsConstructor;
import lombok.Getter;

/*to json example: {"firstName": "John", "lastName": "Doe", "email": "Fh8oQ@example.com", "phone": "1234567890", "street": "123 Main St", "city": "New York", "country": "USA", "zipCode": "12345"}*/
@Getter
@AllArgsConstructor
public final class UpdatePersonalCommand {
    private final String firstName;
    private final String lastName;
    private final String email;
    private final String phone;
    private final String street;
    private final String city;
    private final String country;
    private final String zipCode;
}
