package com.NTTDATA.bootcamp.msvc_customer.application.port.in.validation;

import reactor.core.publisher.Mono;

public interface ICustomerValidationUseCase {
    Mono<Long> totalCustomers();
    Mono<Boolean> emailExists(String email);
    Mono<Boolean> idExists(String id);
    Mono<Long> getCustomerCountByType(String customerType);
    Mono<Boolean> typeDocumentAndNumberExists(String documentType, String numberDocument);
}
