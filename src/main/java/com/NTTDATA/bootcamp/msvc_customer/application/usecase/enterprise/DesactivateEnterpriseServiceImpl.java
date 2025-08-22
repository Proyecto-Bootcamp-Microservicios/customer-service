package com.NTTDATA.bootcamp.msvc_customer.application.usecase.enterprise;

import com.NTTDATA.bootcamp.msvc_customer.application.dto.response.CustomerResponse;
import com.NTTDATA.bootcamp.msvc_customer.application.port.in.enterprise.IDesactivateEnterpriseUseCase;
import com.NTTDATA.bootcamp.msvc_customer.domain.Enterprise;
import com.NTTDATA.bootcamp.msvc_customer.domain.enums.CustomerType;
import com.NTTDATA.bootcamp.msvc_customer.domain.port.out.IEnterpriseRepositoryPort;
import lombok.AllArgsConstructor;
import reactor.core.publisher.Mono;

@AllArgsConstructor
public class DesactivateEnterpriseServiceImpl implements IDesactivateEnterpriseUseCase {

    private final IEnterpriseRepositoryPort enterpriseRepositoryPort;

    @Override
    public Mono<CustomerResponse> desactivate(String id) {
        return enterpriseRepositoryPort.findByIdAndCustomerType(id, CustomerType.ENTERPRISE.name())
                .switchIfEmpty(Mono.error(RuntimeException::new))
                .flatMap(enterprise -> {
                    Enterprise enterpriseToDesactivate = enterprise.desactivate();
                    return enterpriseRepositoryPort.save(enterpriseToDesactivate);
                }
                ).map(e -> new CustomerResponse(e.getId().getValue(), e.getFullName(), e.getCustomerType().name(), e.getDocumentValue(), e.getEmail(), e.isActive(), e.getCreatedAt()));
    }
}
