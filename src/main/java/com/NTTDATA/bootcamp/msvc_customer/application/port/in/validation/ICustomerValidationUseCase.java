package com.NTTDATA.bootcamp.msvc_customer.application.port.in.validation;

import com.NTTDATA.bootcamp.msvc_customer.application.dto.response.CustomerResponse;
import reactor.core.publisher.Mono;

public interface ICustomerValidationUseCase {
    Mono<Long> totalCustomers();
    Mono<Boolean> emailExists(String email);
    Mono<Boolean> idExists(String id);
    Mono<Long> getCustomerCountByType(String customerType);
    Mono<Boolean> typeDocumentAndNumberExists(String documentType, String numberDocument);
    Mono<CustomerResponse> findById(String id);
}
