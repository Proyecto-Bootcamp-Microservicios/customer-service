package com.NTTDATA.bootcamp.msvc_customer.application.usecase.personal;

import com.NTTDATA.bootcamp.msvc_customer.application.dto.command.CreatePersonalCommand;
import com.NTTDATA.bootcamp.msvc_customer.application.dto.response.CustomerResponse;
import com.NTTDATA.bootcamp.msvc_customer.application.port.in.personal.ICreatePersonalUseCase;
import com.NTTDATA.bootcamp.msvc_customer.domain.Personal;
import com.NTTDATA.bootcamp.msvc_customer.domain.port.out.IPersonalRepositoryPort;
import lombok.AllArgsConstructor;
import reactor.core.publisher.Mono;

@AllArgsConstructor
public class CreatePersonalServiceImpl implements ICreatePersonalUseCase {

    private final IPersonalRepositoryPort personalRepositoryPort;

    @Override
    public Mono<CustomerResponse> create(CreatePersonalCommand command) {
        Personal personal = Personal.create(
                command.getFirstName(),
                command.getLastName(),
                command.getDocumentNumber(),
                command.getDocumentType(),
                command.getEmail(),
                command.getPhone(),
                command.getStreet(),
                command.getCity(),
                command.getCountry(),
                command.getZipCode(),
                command.getBirthDate()
        );

        Mono<Personal> savedPersonal = personalRepositoryPort.save(personal);
        return savedPersonal
                .map(sp -> new CustomerResponse(sp.getFirstName(), sp.getLastName(), sp.getCustomerType().name(),
                        sp.getDocumentValue(), sp.getEmail(), sp.isActive(), sp.getCreatedAt()));
    }
}
