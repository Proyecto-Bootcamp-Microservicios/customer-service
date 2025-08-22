package com.NTTDATA.bootcamp.msvc_customer.domain.port.out;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface IGenericRepositoryPort<D, ID> {
    Mono<D> findById(ID id);
    Flux<D> findAll();
    Mono<D> save(D d);
    Mono<Void> delete(ID id);
    Mono<D> findByDocumentTypeAndDocumentNumber(String documentType, String documentNumber);
}
