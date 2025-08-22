package com.NTTDATA.bootcamp.msvc_customer.application.usecase.enterprise;

import com.NTTDATA.bootcamp.msvc_customer.application.dto.command.UpdateEnterpriseCommand;
import com.NTTDATA.bootcamp.msvc_customer.application.dto.response.CustomerResponse;
import com.NTTDATA.bootcamp.msvc_customer.application.port.in.enterprise.IUpdateEnterpriseUseCase;
import com.NTTDATA.bootcamp.msvc_customer.domain.Enterprise;
import com.NTTDATA.bootcamp.msvc_customer.domain.enums.CustomerType;
import com.NTTDATA.bootcamp.msvc_customer.domain.port.out.IEnterpriseRepositoryPort;
import lombok.AllArgsConstructor;
import reactor.core.publisher.Mono;

@AllArgsConstructor
public class UpdateEnterpriseServiceImpl implements IUpdateEnterpriseUseCase {

    private final IEnterpriseRepositoryPort enterpriseRepositoryPort;

    @Override
    public Mono<CustomerResponse> update(String id, UpdateEnterpriseCommand command) {
        return enterpriseRepositoryPort.findByIdAndCustomerType(id, CustomerType.ENTERPRISE.name())
                .switchIfEmpty(Mono.error(RuntimeException::new))
                .flatMap(enterprise -> {
                    Enterprise enterpriseToUpdate = enterprise.update(command.getBusinessName(), command.getEmail(), command.getPhone(), command.getStreet(), command.getCity(), command.getCountry(), command.getZipCode());
                    return enterpriseRepositoryPort.save(enterpriseToUpdate);
                })
                .map(e -> new CustomerResponse(e.getId().getValue(), e.getBusinessName(), e.getCustomerType().name(), e.getDocumentValue(), e.getEmail(), e.isActive(), e.getCreatedAt()));
    }
}
