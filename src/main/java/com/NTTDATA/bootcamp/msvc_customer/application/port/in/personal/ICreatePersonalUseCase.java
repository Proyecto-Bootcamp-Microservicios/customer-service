package com.NTTDATA.bootcamp.msvc_customer.application.port.in.personal;

import com.NTTDATA.bootcamp.msvc_customer.application.dto.command.CreatePersonalCommand;
import com.NTTDATA.bootcamp.msvc_customer.application.dto.response.CustomerResponse;
import reactor.core.publisher.Mono;

public interface ICreatePersonalUseCase {
    Mono<CustomerResponse> create(CreatePersonalCommand command);
}
