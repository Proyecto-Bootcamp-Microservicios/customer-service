package com.NTTDATA.bootcamp.msvc_customer.infrastructure.adapter.out;

import com.NTTDATA.bootcamp.msvc_customer.domain.enums.CustomerType;
import com.NTTDATA.bootcamp.msvc_customer.domain.port.out.ICustomerRepositoryPort;
import com.NTTDATA.bootcamp.msvc_customer.infrastructure.persistence.repository.ISpringCustomerRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
@AllArgsConstructor
public class CustomerRepositoryAdapter implements ICustomerRepositoryPort {

    private final ISpringCustomerRepository repository;

    @Override
    public Mono<Long> totalCustomers() {
        return repository.count();
    }

    @Override
    public Mono<Boolean> emailExists(String email) {
        return repository.existsByEmail(email);
    }

    @Override
    public Mono<Boolean> idExists(String id) {
        return repository.existsById(id);
    }

    @Override
    public Mono<Long> getCustomerCountByType(String customerType) {
        if(customerType == null) return Mono.error(RuntimeException::new);
        if(!customerType.equals(CustomerType.PERSONAL.name()) && !customerType.equals(CustomerType.ENTERPRISE.name())) return Mono.error(RuntimeException::new);

        return repository.countByCustomerType(customerType);
    }

    @Override
    public Mono<Boolean> typeDocumentAndNumberExists(String documentType, String numberDocument) {
        return repository.existsByDocumentTypeAndDocumentNumber(documentType, numberDocument);
    }
}
