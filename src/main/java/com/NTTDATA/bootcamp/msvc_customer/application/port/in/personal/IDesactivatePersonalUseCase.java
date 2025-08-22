package com.NTTDATA.bootcamp.msvc_customer.application.port.in.personal;

import com.NTTDATA.bootcamp.msvc_customer.application.dto.response.CustomerResponse;
import reactor.core.publisher.Mono;

public interface IDesactivatePersonalUseCase {
    Mono<CustomerResponse> desactivate(String id);
}
