package com.NTTDATA.bootcamp.msvc_customer.application.usecase.validation;

import com.NTTDATA.bootcamp.msvc_customer.application.port.in.validation.ICustomerValidationUseCase;
import com.NTTDATA.bootcamp.msvc_customer.domain.port.out.ICustomerRepositoryPort;
import lombok.AllArgsConstructor;
import reactor.core.publisher.Mono;

@AllArgsConstructor
public class CustomerValidationServiceImpl implements ICustomerValidationUseCase {

    private final ICustomerRepositoryPort customerRepositoryPort;

    @Override
    public Mono<Long> totalCustomers() {
        return customerRepositoryPort.totalCustomers();
    }

    @Override
    public Mono<Boolean> emailExists(String email) {
        return customerRepositoryPort.emailExists(email);
    }

    @Override
    public Mono<Boolean> idExists(String id) {
        return customerRepositoryPort.idExists(id);
    }

    @Override
    public Mono<Long> getCustomerCountByType(String customerType) {
        return customerRepositoryPort.getCustomerCountByType(customerType);
    }

    @Override
    public Mono<Boolean> typeDocumentAndNumberExists(String documentType, String numberDocument) {
        return customerRepositoryPort.typeDocumentAndNumberExists(documentType, numberDocument);
    }
}
