package com.NTTDATA.bootcamp.msvc_customer.application.port.in.enterprise;

import com.NTTDATA.bootcamp.msvc_customer.application.dto.command.CreateEnterpriseCommand;
import com.NTTDATA.bootcamp.msvc_customer.application.dto.command.UpdateEnterpriseCommand;
import com.NTTDATA.bootcamp.msvc_customer.application.dto.response.CustomerResponse;
import reactor.core.publisher.Mono;

public interface IUpdateEnterpriseUseCase {
    Mono<CustomerResponse> update(String id, UpdateEnterpriseCommand command);
}
