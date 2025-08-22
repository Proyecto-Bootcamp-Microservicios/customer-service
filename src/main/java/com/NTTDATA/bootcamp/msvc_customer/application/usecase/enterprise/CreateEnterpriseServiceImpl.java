package com.NTTDATA.bootcamp.msvc_customer.application.usecase.enterprise;

import com.NTTDATA.bootcamp.msvc_customer.application.dto.command.CreateEnterpriseCommand;
import com.NTTDATA.bootcamp.msvc_customer.application.dto.response.CustomerResponse;
import com.NTTDATA.bootcamp.msvc_customer.application.port.in.enterprise.ICreateEnterpriseUseCase;
import com.NTTDATA.bootcamp.msvc_customer.domain.Enterprise;
import com.NTTDATA.bootcamp.msvc_customer.domain.port.out.IEnterpriseRepositoryPort;
import lombok.AllArgsConstructor;
import reactor.core.publisher.Mono;

@AllArgsConstructor
public class CreateEnterpriseServiceImpl implements ICreateEnterpriseUseCase {

    private final IEnterpriseRepositoryPort enterpriseRepositoryPort;


    @Override
    public Mono<CustomerResponse> create(CreateEnterpriseCommand command) {
        Enterprise enterprise = Enterprise.create(
                command.getBusinessName(),
                command.getRuc(),
                command.getEmail(),
                command.getPhone(),
                command.getStreet(),
                command.getCity(),
                command.getCountry(),
                command.getZipCode()
        );

        Mono<Enterprise> savedEnterprise = enterpriseRepositoryPort.save(enterprise);
        return savedEnterprise.map(se -> new CustomerResponse(se.getBusinessName(), se.getFullName(), se.getCustomerType().name(), se.getDocumentValue(), se.getEmail(), se.isActive(), se.getCreatedAt()));
    }
}
