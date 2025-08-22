package com.NTTDATA.bootcamp.msvc_customer.infrastructure.adapter.in.rest;

import com.NTTDATA.bootcamp.msvc_customer.application.dto.command.CreateEnterpriseCommand;
import com.NTTDATA.bootcamp.msvc_customer.application.dto.command.CreatePersonalCommand;
import com.NTTDATA.bootcamp.msvc_customer.application.dto.command.UpdateEnterpriseCommand;
import com.NTTDATA.bootcamp.msvc_customer.application.dto.command.UpdatePersonalCommand;
import com.NTTDATA.bootcamp.msvc_customer.application.dto.response.CustomerResponse;
import com.NTTDATA.bootcamp.msvc_customer.application.port.in.enterprise.ICreateEnterpriseUseCase;
import com.NTTDATA.bootcamp.msvc_customer.application.port.in.enterprise.IDesactivateEnterpriseUseCase;
import com.NTTDATA.bootcamp.msvc_customer.application.port.in.enterprise.IUpdateEnterpriseUseCase;
import com.NTTDATA.bootcamp.msvc_customer.application.port.in.personal.ICreatePersonalUseCase;
import com.NTTDATA.bootcamp.msvc_customer.application.port.in.enterprise.IRetriveEnterpriseUseCase;
import com.NTTDATA.bootcamp.msvc_customer.application.port.in.personal.IDesactivatePersonalUseCase;
import com.NTTDATA.bootcamp.msvc_customer.application.port.in.personal.IRetrivePersonalUseCase;
import com.NTTDATA.bootcamp.msvc_customer.application.port.in.personal.IUpdatePersonalUseCase;
import com.NTTDATA.bootcamp.msvc_customer.application.port.in.validation.ICustomerValidationUseCase;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/customers")
@AllArgsConstructor
public class CustomerController {

    private final ICreatePersonalUseCase createPersonalCustomerUseCase;
    private final ICreateEnterpriseUseCase createEnterpriseCustomerUseCase;
    private final IRetrivePersonalUseCase retrivePersonalUseCase;
    private final IRetriveEnterpriseUseCase retriveEnterpriseUseCase;
    private final IDesactivatePersonalUseCase deactivatePersonalUseCase;
    private final IDesactivateEnterpriseUseCase deactivateEnterpriseUseCase;
    private final IUpdatePersonalUseCase updatePersonalUseCase;
    private final IUpdateEnterpriseUseCase updateEnterpriseUseCase;
    private final ICustomerValidationUseCase customerValidationUseCase;

    @GetMapping("/personal")
    public Mono<ResponseEntity<Flux<CustomerResponse>>> retriveAllPersonalCustomers() {
        Flux<CustomerResponse> customerFlux = retrivePersonalUseCase.retriveAllWithoutFilters();
        return Mono.just(ResponseEntity.ok(customerFlux));
    }

    @GetMapping("/personal/{id}")
    public Mono<ResponseEntity<CustomerResponse>> retrievePersonalCustomer(@PathVariable String id) {
        return retrivePersonalUseCase.retrieveById(id)
                .map(ResponseEntity::ok);
    }

    @GetMapping("/personal/document-type/{documentType}/document-number/{documentNumber}")
    public Mono<ResponseEntity<CustomerResponse>> retrievePersonalCustomerByDocument(@PathVariable String documentType, @PathVariable String documentNumber) {
        return retrivePersonalUseCase.retrieveByDocumentTypeAndDocumentNumber(documentType, documentNumber)
                .map(ResponseEntity::ok);
    }

    @GetMapping("/personal/age-min/{ageMin}/age-max/{ageMax}")
    public Mono<ResponseEntity<Flux<CustomerResponse>>> retriveAllPersonalCustomersByAge(@PathVariable int ageMin, @PathVariable int ageMax) {
        Flux<CustomerResponse> customerFlux = retrivePersonalUseCase.retriveAllByAge(ageMin, ageMax);
        return Mono.just(ResponseEntity.ok(customerFlux));
    }

    @GetMapping("/enterprise")
    public Mono<ResponseEntity<Flux<CustomerResponse>>> retriveAllEnterpriseCustomers() {
        Flux<CustomerResponse> customerFlux = retriveEnterpriseUseCase.retriveAllWithoutFilters();
        return Mono.just(ResponseEntity.ok(customerFlux));
    }

    @GetMapping("/enterprise/{id}")
    public Mono<ResponseEntity<CustomerResponse>> retrieveEnterpriseCustomer(@PathVariable String id) {
        return retriveEnterpriseUseCase.retrieveById(id)
                .map(ResponseEntity::ok);
    }

    @GetMapping("/enterprise/document-type/{documentType}/document-number/{documentNumber}")
    public Mono<ResponseEntity<CustomerResponse>> retrieveEnterpriseCustomerByDocument(@PathVariable String documentType, @PathVariable String documentNumber) {
        return retriveEnterpriseUseCase.retrieveByDocumentTypeAndDocumentNumber(documentType, documentNumber)
                .map(ResponseEntity::ok);
    }

    @PostMapping("/personal")
    public Mono<ResponseEntity<CustomerResponse>> createPersonalCustomer(@RequestBody CreatePersonalCommand command, final ServerWebExchange exchange) {
        return createPersonalCustomerUseCase.create(command)
                .map(ResponseEntity::ok);
    }

    @PostMapping("/enterprise")
    public Mono<ResponseEntity<CustomerResponse>> createEnterpriseCustomer(@RequestBody CreateEnterpriseCommand command, final ServerWebExchange exchange) {
        return createEnterpriseCustomerUseCase.create(command)
                .map(ResponseEntity::ok);
    }

    @PutMapping("/personal/desactivate/{id}")
    public Mono<ResponseEntity<CustomerResponse>> desactivatePersonalCustomer(@PathVariable String id) {
        return deactivatePersonalUseCase.desactivate(id)
                .map(ResponseEntity::ok);
    }

    @PutMapping("/personal/update/{id}")
    public Mono<ResponseEntity<CustomerResponse>> updatePersonalCustomer(@PathVariable String id, @RequestBody UpdatePersonalCommand command) {
        return updatePersonalUseCase.update(id, command)
                .map(ResponseEntity::ok);
    }

    @PutMapping("/enterprise/desactivate/{id}")
    public Mono<ResponseEntity<CustomerResponse>> desactivateEnterpriseCustomer(@PathVariable String id) {
        return deactivateEnterpriseUseCase.desactivate(id)
                .map(ResponseEntity::ok);
    }

    @PutMapping("/enterprise/update/{id}")
    public Mono<ResponseEntity<CustomerResponse>> updateEnterpriseCustomer(@PathVariable String id, @RequestBody UpdateEnterpriseCommand command) {
        return updateEnterpriseUseCase.update(id, command)
                .map(ResponseEntity::ok);
    }

    @GetMapping("/count")
    public Mono<ResponseEntity<Long>> totalCustomers() {
        return customerValidationUseCase.totalCustomers()
                .map(ResponseEntity::ok);
    }

    @GetMapping("/email-exists/{email}")
    public Mono<ResponseEntity<Boolean>> emailExists(@PathVariable String email) {
        return customerValidationUseCase.emailExists(email)
                .map(ResponseEntity::ok);
    }

    @GetMapping("/id-exists/{id}")
    public Mono<ResponseEntity<Boolean>> idExists(@PathVariable String id) {
        return customerValidationUseCase.idExists(id)
                .map(ResponseEntity::ok);
    }

    @GetMapping("/count-by-customer-type/{customerType}")
    public Mono<ResponseEntity<Long>> getCustomerCountByType(@PathVariable String customerType) {
        return customerValidationUseCase.getCustomerCountByType(customerType)
                .map(ResponseEntity::ok);
    }

    @GetMapping("/exists/document-type/{documentType}/document-number/{numberDocument}")
    public Mono<ResponseEntity<Boolean>> typeDocumentAndNumberExists(@PathVariable String documentType, @PathVariable String numberDocument) {
        return customerValidationUseCase.typeDocumentAndNumberExists(documentType, numberDocument)
                .map(ResponseEntity::ok);
    }

}
