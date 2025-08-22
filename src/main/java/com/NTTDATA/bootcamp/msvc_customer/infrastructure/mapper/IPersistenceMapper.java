package com.NTTDATA.bootcamp.msvc_customer.infrastructure.mapper;

public interface IPersistenceMapper<D, E> {
    E toEntity(D d);
    D toDomain(E e);
}
