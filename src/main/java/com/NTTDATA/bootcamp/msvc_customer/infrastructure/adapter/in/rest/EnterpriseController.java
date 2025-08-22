package com.NTTDATA.bootcamp.msvc_customer.infrastructure.adapter.in.rest;

import com.NTTDATA.bootcamp.msvc_customer.application.dto.command.CreateEnterpriseCommand;
import com.NTTDATA.bootcamp.msvc_customer.application.dto.command.UpdateEnterpriseCommand;
import com.NTTDATA.bootcamp.msvc_customer.application.port.in.enterprise.ICreateEnterpriseUseCase;
import com.NTTDATA.bootcamp.msvc_customer.application.port.in.enterprise.IDesactivateEnterpriseUseCase;
import com.NTTDATA.bootcamp.msvc_customer.application.port.in.enterprise.IRetriveEnterpriseUseCase;
import com.NTTDATA.bootcamp.msvc_customer.application.port.in.enterprise.IUpdateEnterpriseUseCase;
import com.NTTDATA.bootcamp.msvc_customer.infrastructure.mapper.mapstruct.IRestMapperMapStruct;
import lombok.AllArgsConstructor;
import org.openapitools.api.EnterpriseCustomersApi;
import org.openapitools.model.CreateEnterpriseRequest;
import org.openapitools.model.CustomerResponse;
import org.openapitools.model.UpdateEnterpriseRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.util.UUID;

@AllArgsConstructor
@RestController
public class EnterpriseController implements EnterpriseCustomersApi {
    private final ICreateEnterpriseUseCase createEnterpriseCustomerUseCase;
    private final IRetriveEnterpriseUseCase retriveEnterpriseUseCase;
    private final IDesactivateEnterpriseUseCase deactivateEnterpriseUseCase;
    private final IUpdateEnterpriseUseCase updateEnterpriseUseCase;
    private final IRestMapperMapStruct mapper;

    @Override
    public Mono<ResponseEntity<Flux<CustomerResponse>>> retriveAllEnterpriseCustomers(ServerWebExchange exchange) {
        return retriveEnterpriseUseCase.retriveAllWithoutFilters()
                .map(applicationResponse -> {
                    CustomerResponse customerResponse = mapper
                            .map(applicationResponse);
                    return customerResponse;
                })
                .collectList()
                .map(algo -> ResponseEntity.ok().body(Flux.fromIterable(algo)));
    }

    @Override
    public Mono<ResponseEntity<CustomerResponse>> retrieveEnterpriseCustomer(UUID id, ServerWebExchange exchange) {
        return retriveEnterpriseUseCase.retrieveById(id.toString())
                .map(applicationResponse -> {
                    CustomerResponse customerResponse = mapper
                            .map(applicationResponse);
                    return customerResponse;
                })
                .map(ResponseEntity::ok);
    }

    @Override
    public Mono<ResponseEntity<CustomerResponse>> retrieveEnterpriseCustomerByDocument(String documentNumber, ServerWebExchange exchange) {
        return retriveEnterpriseUseCase.retrieveByDocumentNumber(documentNumber)
                .map(applicationResponse -> {
                    CustomerResponse customerResponse = mapper
                            .map(applicationResponse);
                    return customerResponse;
                })
                .map(ResponseEntity::ok);
    }

    @Override
    public Mono<ResponseEntity<CustomerResponse>> createEnterpriseCustomer(Mono<CreateEnterpriseRequest> createEnterpriseRequest, ServerWebExchange exchange) {
        return createEnterpriseRequest.flatMap(request -> {
            CreateEnterpriseCommand command = mapper.toCreateCommand(request);
            return createEnterpriseCustomerUseCase.create(command)
                    .map(applicationResponse -> {
                        CustomerResponse customerResponse = mapper.map(applicationResponse);
                        return customerResponse;
                    })
                    .flatMap(response -> {
                        URI create = URI.create(exchange.getRequest().getURI() + "/" + response.getId());
                        return Mono.just(ResponseEntity.created(create).body(response));
                    });
        });
    }

    @Override
    public Mono<ResponseEntity<CustomerResponse>> updateEnterpriseCustomer(UUID id, Mono<UpdateEnterpriseRequest> updateEnterpriseRequest, ServerWebExchange exchange) {
        return updateEnterpriseRequest.flatMap(request -> {
            UpdateEnterpriseCommand command = mapper.toUpdateCommand(request);
            Mono<com.NTTDATA.bootcamp.msvc_customer.application.dto.response.CustomerResponse> applicationResponse = updateEnterpriseUseCase.update(id.toString(), command);
            return applicationResponse.map(response -> {
                CustomerResponse customerResponse = mapper.map(response);
                return ResponseEntity.ok(customerResponse);
            });
        });
    }

    @Override
    public Mono<ResponseEntity<CustomerResponse>> desactivateEnterpriseCustomer(UUID id, ServerWebExchange exchange) {
        return deactivateEnterpriseUseCase.desactivate(id.toString())
                .map(applicationResponse -> {
                    CustomerResponse customerResponse = mapper.map(applicationResponse);
                    return ResponseEntity.ok(customerResponse);
                });
    }

}
