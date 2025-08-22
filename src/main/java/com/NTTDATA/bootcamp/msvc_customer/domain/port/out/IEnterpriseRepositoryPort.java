package com.NTTDATA.bootcamp.msvc_customer.domain.port.out;

import com.NTTDATA.bootcamp.msvc_customer.domain.Enterprise;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface IEnterpriseRepositoryPort extends IGenericRepositoryPort<Enterprise, String> {
    Flux<Enterprise> findByCustomerType(String customerType);
    Mono<Enterprise> findByIdAndCustomerType(String id, String customerType);
}
