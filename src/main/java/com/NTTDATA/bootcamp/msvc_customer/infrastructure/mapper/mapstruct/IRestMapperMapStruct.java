package com.NTTDATA.bootcamp.msvc_customer.infrastructure.mapper.mapstruct;

import com.NTTDATA.bootcamp.msvc_customer.application.dto.command.CreateEnterpriseCommand;
import com.NTTDATA.bootcamp.msvc_customer.application.dto.command.CreatePersonalCommand;
import com.NTTDATA.bootcamp.msvc_customer.application.dto.command.UpdateEnterpriseCommand;
import com.NTTDATA.bootcamp.msvc_customer.application.dto.command.UpdatePersonalCommand;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.openapitools.model.*;

@Mapper(componentModel = "spring")
public interface IRestMapperMapStruct {

    IRestMapperMapStruct INSTANCE = Mappers.getMapper(IRestMapperMapStruct.class);

    @Mapping(target = "createdAt", expression = "java(convertToOffsetDateTime(customerResponse.getCreatedAt()))")
    CustomerResponse map(com.NTTDATA.bootcamp.msvc_customer.application.dto.response.CustomerResponse customerResponse);

    CreateEnterpriseCommand toCreateCommand(CreateEnterpriseRequest createEnterpriseRequest);

    UpdateEnterpriseCommand toUpdateCommand(UpdateEnterpriseRequest updateEnterpriseRequest);

    CreatePersonalCommand toCreateCommand(CreatePersonalRequest createPersonalRequest);

    UpdatePersonalCommand toUpdateCommand(UpdatePersonalRequest updatePersonalRequest);

    default java.time.OffsetDateTime convertToOffsetDateTime(java.time.LocalDateTime localDateTime) {
        if (localDateTime == null) {
            return null;
        }
        return localDateTime.atOffset(java.time.ZoneOffset.UTC);
    }

}
