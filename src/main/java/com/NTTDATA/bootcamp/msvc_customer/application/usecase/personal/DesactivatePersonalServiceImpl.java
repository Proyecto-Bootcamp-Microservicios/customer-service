package com.NTTDATA.bootcamp.msvc_customer.application.usecase.personal;

import com.NTTDATA.bootcamp.msvc_customer.application.dto.response.CustomerResponse;
import com.NTTDATA.bootcamp.msvc_customer.application.port.in.personal.IDesactivatePersonalUseCase;
import com.NTTDATA.bootcamp.msvc_customer.domain.Personal;
import com.NTTDATA.bootcamp.msvc_customer.domain.enums.CustomerType;
import com.NTTDATA.bootcamp.msvc_customer.domain.port.out.IPersonalRepositoryPort;

import lombok.AllArgsConstructor;
import reactor.core.publisher.Mono;

@AllArgsConstructor
public class DesactivatePersonalServiceImpl implements IDesactivatePersonalUseCase {

    private final IPersonalRepositoryPort personalRepositoryPort;

    @Override
    public Mono<CustomerResponse> desactivate(String id) {
        return personalRepositoryPort.findByIdAndCustomerType(id, CustomerType.PERSONAL.name())
                .switchIfEmpty(Mono.error(RuntimeException::new))
                .flatMap(personal -> {
                    Personal personalToDesactivate = personal.desactivate();
                    return personalRepositoryPort.save(personalToDesactivate);
                }).map(p -> new CustomerResponse(p.getId().getValue(), p.getFirstName(), p.getCustomerType().name(), p.getDocumentValue(), p.getEmail(), p.isActive(), p.getCreatedAt()));
    }
}
