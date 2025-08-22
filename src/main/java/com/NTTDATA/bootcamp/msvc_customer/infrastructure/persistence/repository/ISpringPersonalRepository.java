package com.NTTDATA.bootcamp.msvc_customer.infrastructure.persistence.repository;

import com.NTTDATA.bootcamp.msvc_customer.infrastructure.persistence.entity.PersonalCollection;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDate;

public interface ISpringPersonalRepository extends ISpringGenericRepository<PersonalCollection, String> {
    Flux<PersonalCollection> findByCustomerType(String customerType);

    Mono<PersonalCollection> findByIdAndCustomerType(String id, String customerType);
    Flux<PersonalCollection> findByBirthDateBetween(LocalDate startDate, LocalDate endDate);
}
