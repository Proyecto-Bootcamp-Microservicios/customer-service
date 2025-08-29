package com.NTTDATA.bootcamp.msvc_customer.infrastructure.mapper;

import com.NTTDATA.bootcamp.msvc_customer.domain.Personal;
import com.NTTDATA.bootcamp.msvc_customer.domain.enums.DocumentType;
import com.NTTDATA.bootcamp.msvc_customer.infrastructure.persistence.entity.PersonalCollection;
import org.springframework.stereotype.Component;

@Component
public class PersonalPersistenceMapperImpl implements IPersonalPersistenceMapper {

    @Override
    public PersonalCollection toEntity(Personal personal) {
        return new PersonalCollection(
                personal.getId().getValue(),
                personal.getName(),
                personal.getIdentificationDocument().getType().name(),
                personal.getIdentificationDocument().getValue(),
                personal.getContactInfo().getEmail(),
                personal.getContactInfo().getPhone(),
                personal.getContactInfo().getAddress().getStreet(),
                personal.getContactInfo().getAddress().getCity(),
                personal.getContactInfo().getAddress().getCountry(),
                personal.getContactInfo().getAddress().getZipCode(),
                personal.isActive(),
                personal.getAudit().getCreatedAt(),
                personal.getAudit().getUpdatedAt(),
                personal.getFirstName(),
                personal.getLastName(),
                personal.getBirthDate()
        );
    }

    @Override
    public Personal toDomain(PersonalCollection personalCollection) {
        System.out.println("DEBUG Mapper - personalCollection.getId() = " + personalCollection.getId());
        System.out.println("DEBUG Mapper - personalCollection.getFirstName() = " + personalCollection.getFirstName());
        System.out.println("DEBUG Mapper - personalCollection.getLastName() = " + personalCollection.getLastName());
        System.out.println("DEBUG Mapper - personalCollection.getIdentificationDocument() = " + personalCollection.getDocumentNumber());
        System.out.println("DEBUG Mapper - personalCollection.getDocumentType() = " + personalCollection.getDocumentType());
        System.out.println("DEBUG Mapper - personalCollection.getEmail() = " + personalCollection.getEmail());
        System.out.println("DEBUG Mapper - personalCollection.getPhone() = " + personalCollection.getPhone());
        System.out.println("DEBUG Mapper - personalCollection.getStreet() = " + personalCollection.getStreet());
        System.out.println("DEBUG Mapper - personalCollection.getCity() = " + personalCollection.getCity());
        System.out.println("DEBUG Mapper - personalCollection.getCountry() = " + personalCollection.getCountry());
        System.out.println("DEBUG Mapper - personalCollection.getZipCode() = " + personalCollection.getZipCode());
        System.out.println("DEBUG Mapper - personalCollection.getBirthDate() = " + personalCollection.getBirthDate());
        System.out.println("DEBUG Mapper - personalCollection.getCreatedAt() = " + personalCollection.getCreatedAt());
        System.out.println("DEBUG Mapper - personalCollection.getUpdatedAt() = " + personalCollection.getUpdatedAt());
        System.out.println("DEBUG Mapper - personalCollection.isActive() = " + personalCollection.isActive());
        return Personal.reconstruct(
                personalCollection.getId(),
                personalCollection.getFirstName(),
                personalCollection.getLastName(),
                personalCollection.getDocumentNumber(),
                DocumentType.valueOf(personalCollection.getDocumentType()),
                personalCollection.getEmail(),
                personalCollection.getPhone(),
                personalCollection.getStreet(),
                personalCollection.getCity(),
                personalCollection.getCountry(),
                personalCollection.getZipCode(),
                personalCollection.getBirthDate(),
                personalCollection.getCreatedAt(),
                personalCollection.getUpdatedAt(),
                personalCollection.isActive()
        );
    }
}
