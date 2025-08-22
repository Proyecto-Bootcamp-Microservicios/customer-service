package com.NTTDATA.bootcamp.msvc_customer.application.port.in.enterprise;

import com.NTTDATA.bootcamp.msvc_customer.application.dto.command.CreateEnterpriseCommand;
import com.NTTDATA.bootcamp.msvc_customer.application.dto.response.CustomerResponse;
import reactor.core.publisher.Mono;

public interface ICreateEnterpriseUseCase {
    Mono<CustomerResponse> create(CreateEnterpriseCommand command);
}
