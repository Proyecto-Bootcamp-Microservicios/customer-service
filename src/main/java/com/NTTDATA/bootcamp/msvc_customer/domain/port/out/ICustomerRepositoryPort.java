package com.NTTDATA.bootcamp.msvc_customer.domain.port.out;

import reactor.core.publisher.Mono;

public interface ICustomerRepositoryPort {
    Mono<Long> totalCustomers();
    Mono<Boolean> emailExists(String email);
    Mono<Boolean> idExists(String id);
    Mono<Long> getCustomerCountByType(String customerType);
    Mono<Boolean> typeDocumentAndNumberExists(String documentType, String numberDocument);
}
