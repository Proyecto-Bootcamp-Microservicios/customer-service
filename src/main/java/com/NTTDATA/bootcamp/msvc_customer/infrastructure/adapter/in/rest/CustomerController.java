package com.NTTDATA.bootcamp.msvc_customer.infrastructure.adapter.in.rest;

import com.NTTDATA.bootcamp.msvc_customer.application.port.in.validation.ICustomerValidationUseCase;
import lombok.AllArgsConstructor;
import org.openapitools.api.CustomerQueriesApi;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.UUID;

@RestController
@AllArgsConstructor
public class CustomerController implements CustomerQueriesApi {

    private final ICustomerValidationUseCase customerValidationUseCase;

    @Override
    public Mono<ResponseEntity<Integer>> getCustomerCountByType(String customerType, ServerWebExchange exchange) {
        return customerValidationUseCase.getCustomerCountByType(customerType)
                .map(response -> ResponseEntity.ok(response.intValue()));
    }

    @Override
    public Mono<ResponseEntity<Boolean>> idExists(UUID id, ServerWebExchange exchange) {
        return customerValidationUseCase.idExists(id.toString())
                .map(ResponseEntity::ok);
    }

    @Override
    public Mono<ResponseEntity<Boolean>> emailExists(String email, ServerWebExchange exchange) {
        return customerValidationUseCase.emailExists(email)
                .map(ResponseEntity::ok);
    }

    @Override
    public Mono<ResponseEntity<Integer>> totalCustomers(ServerWebExchange exchange) {
        return customerValidationUseCase.totalCustomers()
                .map(response -> ResponseEntity.ok(response.intValue()));
    }

    @Override
    public Mono<ResponseEntity<Boolean>> typeDocumentAndNumberExists(String documentType, String numberDocument, ServerWebExchange exchange) {
        return customerValidationUseCase.typeDocumentAndNumberExists(documentType, numberDocument)
                .map(ResponseEntity::ok);
    }

}
