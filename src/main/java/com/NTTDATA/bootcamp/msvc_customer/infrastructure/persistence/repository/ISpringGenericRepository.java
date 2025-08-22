package com.NTTDATA.bootcamp.msvc_customer.infrastructure.persistence.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.data.repository.NoRepositoryBean;
import reactor.core.publisher.Mono;

@NoRepositoryBean
public interface ISpringGenericRepository<E, ID> extends ReactiveMongoRepository<E, ID> {
    Mono<E> findByCustomerTypeAndDocumentTypeAndDocumentNumber(String customerType, String documentNumber, String documentType);
}
