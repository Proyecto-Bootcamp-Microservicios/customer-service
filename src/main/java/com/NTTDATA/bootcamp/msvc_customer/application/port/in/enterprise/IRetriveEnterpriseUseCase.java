package com.NTTDATA.bootcamp.msvc_customer.application.port.in.enterprise;

import com.NTTDATA.bootcamp.msvc_customer.application.dto.response.CustomerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface IRetriveEnterpriseUseCase {

    Flux<CustomerResponse> retriveAllWithoutFilters();
    Mono<CustomerResponse> retrieveById(String id);
    Mono<CustomerResponse> retrieveByDocumentNumber(String documentNumber);

}
