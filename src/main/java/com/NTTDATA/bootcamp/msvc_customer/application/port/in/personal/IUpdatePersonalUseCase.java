package com.NTTDATA.bootcamp.msvc_customer.application.port.in.personal;

import com.NTTDATA.bootcamp.msvc_customer.application.dto.command.UpdatePersonalCommand;
import com.NTTDATA.bootcamp.msvc_customer.application.dto.response.CustomerResponse;
import reactor.core.publisher.Mono;

public interface IUpdatePersonalUseCase {
    Mono<CustomerResponse> update(String id, UpdatePersonalCommand command);
}
