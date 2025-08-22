package com.NTTDATA.bootcamp.msvc_customer.infrastructure.adapter.out;

import com.NTTDATA.bootcamp.msvc_customer.domain.Personal;
import com.NTTDATA.bootcamp.msvc_customer.domain.enums.CustomerType;
import com.NTTDATA.bootcamp.msvc_customer.domain.port.out.IPersonalRepositoryPort;
import com.NTTDATA.bootcamp.msvc_customer.infrastructure.mapper.IPersonalPersistenceMapper;
import com.NTTDATA.bootcamp.msvc_customer.infrastructure.persistence.entity.PersonalCollection;
import com.NTTDATA.bootcamp.msvc_customer.infrastructure.persistence.repository.ISpringPersonalRepository;
import com.NTTDATA.bootcamp.msvc_customer.infrastructure.persistence.repository.impl.AbstractSpringRepositoryImpl;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDate;

@Repository
public class PersonalRepositoryAdapter extends AbstractSpringRepositoryImpl<Personal, PersonalCollection, String> implements IPersonalRepositoryPort {

    private final ISpringPersonalRepository repository;
    private final IPersonalPersistenceMapper mapper;

    protected PersonalRepositoryAdapter(ISpringPersonalRepository repository, IPersonalPersistenceMapper mapper) {
        super(repository, mapper);
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public Flux<Personal> findByCustomerType(String customerType) {
        return repository.findByCustomerType(customerType).map(mapper::toDomain);
    }

    @Override
    public Mono<Personal> findByIdAndCustomerType(String id, String customerType) {
        return repository.findByIdAndCustomerType(id, customerType).map(mapper::toDomain);
    }

    @Override
    public Flux<Personal> findAllByByAgeRange(int minAge, int maxAge) {
        LocalDate startDate = LocalDate.now().minusYears(maxAge);
        LocalDate endDate = LocalDate.now().minusYears(minAge + 1).plusDays(1);
        return repository.findByBirthDateBetween(startDate, endDate).map(mapper::toDomain);
    }

}
