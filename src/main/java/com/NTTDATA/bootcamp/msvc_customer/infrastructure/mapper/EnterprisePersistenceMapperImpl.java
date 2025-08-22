package com.NTTDATA.bootcamp.msvc_customer.infrastructure.mapper;

import com.NTTDATA.bootcamp.msvc_customer.domain.Enterprise;
import com.NTTDATA.bootcamp.msvc_customer.infrastructure.persistence.entity.EnterpriseCollection;
import org.springframework.stereotype.Component;

@Component
public class EnterprisePersistenceMapperImpl implements IEnterprisePersistenceMapper {
    @Override
    public EnterpriseCollection toEntity(Enterprise enterprise) {
        return new EnterpriseCollection(
                enterprise.getId().getValue(),
                enterprise.getName(),
                enterprise.getDocumentType(),
                enterprise.getDocumentValue(),
                enterprise.getEmail(),
                enterprise.getPhone(),
                enterprise.getStreet(),
                enterprise.getCity(),
                enterprise.getCountry(),
                enterprise.getZipCode(),
                enterprise.isActive(),
                enterprise.getCreatedAt(),
                enterprise.getUpdatedAt(),
                enterprise.getBusinessName()
        );
    }

    @Override
    public Enterprise toDomain(EnterpriseCollection enterpriseCollection) {
        return Enterprise.reconstruct(
                enterpriseCollection.getId(),
                enterpriseCollection.getName(),
                enterpriseCollection.getDocumentNumber(),
                enterpriseCollection.getEmail(),
                enterpriseCollection.getPhone(),
                enterpriseCollection.getStreet(),
                enterpriseCollection.getCity(),
                enterpriseCollection.getCountry(),
                enterpriseCollection.getZipCode(),
                enterpriseCollection.getCreatedAt(),
                enterpriseCollection.getUpdatedAt(),
                enterpriseCollection.isActive()
        );
    }
}
