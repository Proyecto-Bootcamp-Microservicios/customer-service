package com.NTTDATA.bootcamp.msvc_customer.infrastructure.persistence.repository;

import com.NTTDATA.bootcamp.msvc_customer.infrastructure.persistence.entity.EnterpriseCollection;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ISpringEnterpriseRepository extends ISpringGenericRepository<EnterpriseCollection, String> {
    Flux<EnterpriseCollection> findByCustomerType(String customerType);
    Mono<EnterpriseCollection> findByIdAndCustomerType(String id, String customerType);
}
