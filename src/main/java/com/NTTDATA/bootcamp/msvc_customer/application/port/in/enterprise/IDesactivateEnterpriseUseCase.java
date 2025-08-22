package com.NTTDATA.bootcamp.msvc_customer.application.port.in.enterprise;

import com.NTTDATA.bootcamp.msvc_customer.application.dto.response.CustomerResponse;
import reactor.core.publisher.Mono;

public interface IDesactivateEnterpriseUseCase {
    Mono<CustomerResponse> desactivate(String id);
}
