package com.NTTDATA.bootcamp.msvc_customer.infrastructure.adapter.in.rest;

import com.NTTDATA.bootcamp.msvc_customer.application.dto.command.CreatePersonalCommand;
import com.NTTDATA.bootcamp.msvc_customer.application.dto.command.UpdatePersonalCommand;
import com.NTTDATA.bootcamp.msvc_customer.application.port.in.personal.ICreatePersonalUseCase;
import com.NTTDATA.bootcamp.msvc_customer.application.port.in.personal.IDesactivatePersonalUseCase;
import com.NTTDATA.bootcamp.msvc_customer.application.port.in.personal.IRetrivePersonalUseCase;
import com.NTTDATA.bootcamp.msvc_customer.application.port.in.personal.IUpdatePersonalUseCase;
import com.NTTDATA.bootcamp.msvc_customer.infrastructure.mapper.mapstruct.IRestMapperMapStruct;
import lombok.AllArgsConstructor;
import org.openapitools.api.PersonalCustomersApi;
import org.openapitools.model.CreatePersonalRequest;
import org.openapitools.model.CustomerResponse;
import org.openapitools.model.UpdatePersonalRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

@AllArgsConstructor
@RestController
public class PersonalController implements PersonalCustomersApi {

    private final ICreatePersonalUseCase createPersonalCustomerUseCase;
    private final IRetrivePersonalUseCase retrivePersonalUseCase;
    private final IDesactivatePersonalUseCase deactivatePersonalUseCase;
    private final IUpdatePersonalUseCase updatePersonalUseCase;
    private final IRestMapperMapStruct restMapperMapStruct;

    @Override
    public Mono<ResponseEntity<Flux<CustomerResponse>>> retriveAllPersonalCustomers(ServerWebExchange exchange) {
        return retrivePersonalUseCase.retriveAllWithoutFilters()
                .map(restMapperMapStruct::map)
                .collectList()
                .map(algo -> ResponseEntity.ok(Flux.fromIterable(algo)));
    }

    @Override
    public Mono<ResponseEntity<CustomerResponse>> retrievePersonalCustomer(UUID id, ServerWebExchange exchange) {
        return retrivePersonalUseCase.retrieveById(id.toString())
                .map(restMapperMapStruct::map)
                .map(ResponseEntity::ok);
    }

    @Override
    public Mono<ResponseEntity<CustomerResponse>> retrievePersonalCustomerByDocument(String documentType, String documentNumber, ServerWebExchange exchange) {
        return PersonalCustomersApi.super.retrievePersonalCustomerByDocument(documentType, documentNumber, exchange);
    }

    @Override
    public Mono<ResponseEntity<CustomerResponse>> createPersonalCustomer(Mono<CreatePersonalRequest> createPersonalRequest, ServerWebExchange exchange) {
        return createPersonalRequest.flatMap(request -> {
            CreatePersonalCommand command = restMapperMapStruct.toCreateCommand(request);
            Mono<com.NTTDATA.bootcamp.msvc_customer.application.dto.response.CustomerResponse> applicationResponse = createPersonalCustomerUseCase.create(command);
            return applicationResponse.map(response -> {
                CustomerResponse customerResponse = restMapperMapStruct.map(response);
                return ResponseEntity.ok(customerResponse);
            });
        });
    }

    @Override
    public Mono<ResponseEntity<CustomerResponse>> updatePersonalCustomer(UUID id, Mono<UpdatePersonalRequest> updatePersonalRequest, ServerWebExchange exchange) {
        return updatePersonalRequest.flatMap(request -> {
            UpdatePersonalCommand command = restMapperMapStruct.toUpdateCommand(request);
            Mono<com.NTTDATA.bootcamp.msvc_customer.application.dto.response.CustomerResponse> applicationResponse = updatePersonalUseCase.update(id.toString(), command);
            return applicationResponse.map(response -> {
                CustomerResponse customerResponse = restMapperMapStruct.map(response);
                return ResponseEntity.ok(customerResponse);
            });
        });
    }

    @Override
    public Mono<ResponseEntity<CustomerResponse>> desactivatePersonalCustomer(UUID id, ServerWebExchange exchange) {
        return deactivatePersonalUseCase.desactivate(id.toString())
                .map(restMapperMapStruct::map)
                .map(ResponseEntity::ok);
    }
}
