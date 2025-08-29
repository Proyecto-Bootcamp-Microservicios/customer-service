package com.NTTDATA.bootcamp.msvc_customer.infrastructure.adapter.out;

import com.NTTDATA.bootcamp.msvc_customer.domain.Customer;
import com.NTTDATA.bootcamp.msvc_customer.domain.enums.CustomerType;
import com.NTTDATA.bootcamp.msvc_customer.domain.port.out.ICustomerRepositoryPort;
import com.NTTDATA.bootcamp.msvc_customer.infrastructure.mapper.IEnterprisePersistenceMapper;
import com.NTTDATA.bootcamp.msvc_customer.infrastructure.mapper.IPersonalPersistenceMapper;
import com.NTTDATA.bootcamp.msvc_customer.infrastructure.persistence.entity.EnterpriseCollection;
import com.NTTDATA.bootcamp.msvc_customer.infrastructure.persistence.entity.PersonalCollection;
import com.NTTDATA.bootcamp.msvc_customer.infrastructure.persistence.repository.ISpringCustomerRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
@AllArgsConstructor
public class CustomerRepositoryAdapter implements ICustomerRepositoryPort {

    private final ISpringCustomerRepository repository;
    private final IPersonalPersistenceMapper personalPersistenceMapper;
    private final IEnterprisePersistenceMapper enterprisePersistenceMapper;

    @Override
    public Mono<Customer> findById(String id) {
        Mono<Customer> customer = repository.findById(id)
                .map(entity -> {
                    if(entity instanceof PersonalCollection) {
                        return personalPersistenceMapper.toDomain((PersonalCollection) entity);
                    } else {
                        return enterprisePersistenceMapper.toDomain((EnterpriseCollection) entity);
                    }
                });
        return customer;
    }

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
