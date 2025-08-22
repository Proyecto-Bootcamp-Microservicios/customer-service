package com.NTTDATA.bootcamp.msvc_customer.infrastructure.adapter.out;

import com.NTTDATA.bootcamp.msvc_customer.domain.Enterprise;
import com.NTTDATA.bootcamp.msvc_customer.domain.port.out.IEnterpriseRepositoryPort;
import com.NTTDATA.bootcamp.msvc_customer.infrastructure.mapper.IEnterprisePersistenceMapper;
import com.NTTDATA.bootcamp.msvc_customer.infrastructure.persistence.entity.EnterpriseCollection;
import com.NTTDATA.bootcamp.msvc_customer.infrastructure.persistence.repository.ISpringEnterpriseRepository;
import com.NTTDATA.bootcamp.msvc_customer.infrastructure.persistence.repository.impl.AbstractSpringRepositoryImpl;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public class EnterpriseRepositoryAdapter extends AbstractSpringRepositoryImpl<Enterprise, EnterpriseCollection, String> implements IEnterpriseRepositoryPort {

    private final ISpringEnterpriseRepository repository;
    private final IEnterprisePersistenceMapper mapper;

    public EnterpriseRepositoryAdapter(ISpringEnterpriseRepository repository, IEnterprisePersistenceMapper mapper) {
        super(repository, mapper);
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public Flux<Enterprise> findByCustomerType(String customerType) {
        return repository.findByCustomerType(customerType).map(mapper::toDomain);
    }

    @Override
    public Mono<Enterprise> findByIdAndCustomerType(String id, String customerType) {
        return repository.findByIdAndCustomerType(id, customerType).map(mapper::toDomain);
    }

}
