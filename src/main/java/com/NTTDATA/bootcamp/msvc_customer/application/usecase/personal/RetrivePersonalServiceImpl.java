package com.NTTDATA.bootcamp.msvc_customer.application.usecase.personal;

import com.NTTDATA.bootcamp.msvc_customer.application.dto.response.CustomerResponse;
import com.NTTDATA.bootcamp.msvc_customer.application.port.in.personal.IRetrivePersonalUseCase;
import com.NTTDATA.bootcamp.msvc_customer.domain.Personal;
import com.NTTDATA.bootcamp.msvc_customer.domain.enums.CustomerType;
import com.NTTDATA.bootcamp.msvc_customer.domain.port.out.IPersonalRepositoryPort;
import lombok.AllArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@AllArgsConstructor
public class RetrivePersonalServiceImpl implements IRetrivePersonalUseCase {

    private final IPersonalRepositoryPort personalRepositoryPort;

    @Override
    public Flux<CustomerResponse> retriveAllWithoutFilters() {
        Flux<Personal> personalFlux = personalRepositoryPort.findByCustomerType(CustomerType.PERSONAL.name());
        return personalFlux.map(
                p -> new CustomerResponse(p.getId().getValue(), p.getFullName(), p.getCustomerType().name(), p.getDocumentValue(), p.getEmail(), p.isActive(), p.getCreatedAt())
        );
    }

    @Override
    public Mono<CustomerResponse> retrieveById(String id) {
        return personalRepositoryPort.findByIdAndCustomerType(id, CustomerType.PERSONAL.name()).map(
                p -> new CustomerResponse(p.getId().getValue(), p.getFullName(), p.getCustomerType().name(), p.getDocumentValue(), p.getEmail(), p.isActive(), p.getCreatedAt())
        );
    }

    @Override
    public Mono<CustomerResponse> retrieveByDocumentTypeAndDocumentNumber(String documentType, String documentNumber) {
        return personalRepositoryPort.findByDocumentTypeAndDocumentNumber(documentType, documentNumber).map(
                p -> new CustomerResponse(p.getId().getValue(), p.getFullName(), p.getCustomerType().name(), p.getDocumentValue(), p.getEmail(), p.isActive(), p.getCreatedAt()));
    }

    @Override
    public Flux<CustomerResponse> retriveAllByAge(int ageMin, int ageMax) {
        if(ageMin > ageMax) throw new IllegalArgumentException("ageMin cannot be greater than ageMax");
        if(ageMin < 18) ageMin = Personal.MIN_AGE;
        if(ageMax > Personal.MAX_AGE) ageMax = Personal.MAX_AGE;
        return personalRepositoryPort.findAllByByAgeRange(ageMin, ageMax).map(
                p -> new CustomerResponse(p.getId().getValue(), p.getFullName(), p.getCustomerType().name(), p.getDocumentValue(), p.getEmail(), p.isActive(), p.getCreatedAt()));
    }
}
