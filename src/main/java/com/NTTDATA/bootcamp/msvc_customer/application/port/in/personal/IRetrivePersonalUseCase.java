package com.NTTDATA.bootcamp.msvc_customer.application.port.in.personal;

import com.NTTDATA.bootcamp.msvc_customer.application.dto.response.CustomerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface IRetrivePersonalUseCase {
    Flux<CustomerResponse> retriveAllWithoutFilters();
    Mono<CustomerResponse> retrieveById(String id);
    Mono<CustomerResponse> retrieveByDocumentTypeAndDocumentNumber(String documentType, String documentNumber);
    Flux<CustomerResponse> retriveAllByAge(int ageMin, int ageMax);
}
