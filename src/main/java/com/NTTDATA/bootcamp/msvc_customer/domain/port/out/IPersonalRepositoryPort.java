package com.NTTDATA.bootcamp.msvc_customer.domain.port.out;

import com.NTTDATA.bootcamp.msvc_customer.domain.Personal;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface IPersonalRepositoryPort extends IGenericRepositoryPort<Personal, String> {
    Flux<Personal> findByCustomerType(String customerType);
    Mono<Personal> findByIdAndCustomerType(String id, String customerType);
    Flux<Personal> findAllByByAgeRange(int minAge, int maxAge);
}
