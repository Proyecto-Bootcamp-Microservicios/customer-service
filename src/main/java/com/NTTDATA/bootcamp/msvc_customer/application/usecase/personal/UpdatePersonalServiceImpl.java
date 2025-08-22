package com.NTTDATA.bootcamp.msvc_customer.application.usecase.personal;

import com.NTTDATA.bootcamp.msvc_customer.application.dto.command.UpdatePersonalCommand;
import com.NTTDATA.bootcamp.msvc_customer.application.dto.response.CustomerResponse;
import com.NTTDATA.bootcamp.msvc_customer.application.port.in.personal.IUpdatePersonalUseCase;
import com.NTTDATA.bootcamp.msvc_customer.domain.Personal;
import com.NTTDATA.bootcamp.msvc_customer.domain.enums.CustomerType;
import com.NTTDATA.bootcamp.msvc_customer.domain.port.out.IPersonalRepositoryPort;
import lombok.AllArgsConstructor;
import reactor.core.publisher.Mono;

@AllArgsConstructor
public class UpdatePersonalServiceImpl implements IUpdatePersonalUseCase {

    private final IPersonalRepositoryPort personalRepositoryPort;

    @Override
    public Mono<CustomerResponse> update(String id, UpdatePersonalCommand command) {
        return personalRepositoryPort.findByIdAndCustomerType(id, CustomerType.PERSONAL.name())
                .switchIfEmpty(Mono.error(RuntimeException::new))
                .flatMap(personal -> {
                    Personal personalToUpdate = personal.update(command.getFirstName(), command.getLastName(), command.getEmail(), command.getPhone(), command.getStreet(), command.getCity(), command.getCountry(), command.getZipCode());
                    return personalRepositoryPort.save(personalToUpdate);
                })
                .map(p -> new CustomerResponse(p.getId().getValue(), p.getFirstName(), p.getCustomerType().name(), p.getDocumentValue(), p.getEmail(), p.isActive(), p.getCreatedAt()));
    }
}
