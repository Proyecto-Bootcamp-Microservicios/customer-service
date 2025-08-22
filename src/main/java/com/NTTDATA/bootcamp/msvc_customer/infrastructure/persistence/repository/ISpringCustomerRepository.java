package com.NTTDATA.bootcamp.msvc_customer.infrastructure.persistence.repository;

import com.NTTDATA.bootcamp.msvc_customer.infrastructure.persistence.entity.CustomerCollection;
import reactor.core.publisher.Mono;

public interface ISpringCustomerRepository extends ISpringGenericRepository<CustomerCollection, String> {
    Mono<Boolean> existsByEmail(String email);
    Mono<Long> countByCustomerType(String customerType);
    Mono<Boolean> existsByDocumentTypeAndDocumentNumber(String documentType, String documentNumber);
}
