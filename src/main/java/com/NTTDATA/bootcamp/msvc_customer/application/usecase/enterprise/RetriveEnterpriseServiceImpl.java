package com.NTTDATA.bootcamp.msvc_customer.application.usecase.enterprise;

import com.NTTDATA.bootcamp.msvc_customer.application.dto.response.CustomerResponse;
import com.NTTDATA.bootcamp.msvc_customer.application.port.in.enterprise.IRetriveEnterpriseUseCase;
import com.NTTDATA.bootcamp.msvc_customer.domain.enums.CustomerType;
import com.NTTDATA.bootcamp.msvc_customer.domain.enums.DocumentType;
import com.NTTDATA.bootcamp.msvc_customer.domain.port.out.IEnterpriseRepositoryPort;
import lombok.AllArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@AllArgsConstructor
public class RetriveEnterpriseServiceImpl implements IRetriveEnterpriseUseCase {

    private final IEnterpriseRepositoryPort enterpriseRepositoryPort;

    @Override
    public Flux<CustomerResponse> retriveAllWithoutFilters() {
        return enterpriseRepositoryPort.findByCustomerType(CustomerType.ENTERPRISE.name())
                .map(e -> new CustomerResponse(e.getId().getValue(), e.getFullName(), e.getCustomerType().name(), e.getDocumentValue(), e.getEmail(), e.isActive(), e.getCreatedAt()));
    }

    @Override
    public Mono<CustomerResponse> retrieveById(String id) {
        return enterpriseRepositoryPort.findByIdAndCustomerType(id, CustomerType.ENTERPRISE.name()).map(e -> new CustomerResponse(e.getId().getValue(), e.getFullName(), e.getCustomerType().name(), e.getDocumentValue(), e.getEmail(), e.isActive(), e.getCreatedAt()));
    }

    @Override
    public Mono<CustomerResponse> retrieveByDocumentNumber(String documentNumber) {
        return enterpriseRepositoryPort.findByDocumentTypeAndDocumentNumber(DocumentType.RUC.name(), documentNumber).map(e -> new CustomerResponse(e.getId().getValue(), e.getFullName(), e.getCustomerType().name(), e.getDocumentValue(), e.getEmail(), e.isActive(), e.getCreatedAt()));
    }
}
