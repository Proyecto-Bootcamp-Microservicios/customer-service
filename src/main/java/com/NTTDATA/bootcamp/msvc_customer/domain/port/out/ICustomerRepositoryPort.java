package com.NTTDATA.bootcamp.msvc_customer.domain.port.out;

import com.NTTDATA.bootcamp.msvc_customer.domain.Customer;
import reactor.core.publisher.Mono;

public interface ICustomerRepositoryPort {
    Mono<Customer> findById(String id);
    Mono<Long> totalCustomers();
    Mono<Boolean> emailExists(String email);
    Mono<Boolean> idExists(String id);
    Mono<Long> getCustomerCountByType(String customerType);
    Mono<Boolean> typeDocumentAndNumberExists(String documentType, String numberDocument);
}
