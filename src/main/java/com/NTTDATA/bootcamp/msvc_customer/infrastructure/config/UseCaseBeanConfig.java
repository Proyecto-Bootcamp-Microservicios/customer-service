package com.NTTDATA.bootcamp.msvc_customer.infrastructure.config;

import com.NTTDATA.bootcamp.msvc_customer.application.port.in.enterprise.ICreateEnterpriseUseCase;
import com.NTTDATA.bootcamp.msvc_customer.application.port.in.enterprise.IUpdateEnterpriseUseCase;
import com.NTTDATA.bootcamp.msvc_customer.application.port.in.personal.ICreatePersonalUseCase;
import com.NTTDATA.bootcamp.msvc_customer.application.port.in.enterprise.IRetriveEnterpriseUseCase;
import com.NTTDATA.bootcamp.msvc_customer.application.port.in.enterprise.IDesactivateEnterpriseUseCase;
import com.NTTDATA.bootcamp.msvc_customer.application.port.in.personal.IDesactivatePersonalUseCase;
import com.NTTDATA.bootcamp.msvc_customer.application.port.in.personal.IRetrivePersonalUseCase;
import com.NTTDATA.bootcamp.msvc_customer.application.port.in.personal.IUpdatePersonalUseCase;
import com.NTTDATA.bootcamp.msvc_customer.application.port.in.validation.ICustomerValidationUseCase;
import com.NTTDATA.bootcamp.msvc_customer.application.usecase.enterprise.CreateEnterpriseServiceImpl;
import com.NTTDATA.bootcamp.msvc_customer.application.usecase.enterprise.DesactivateEnterpriseServiceImpl;
import com.NTTDATA.bootcamp.msvc_customer.application.usecase.enterprise.RetriveEnterpriseServiceImpl;
import com.NTTDATA.bootcamp.msvc_customer.application.usecase.enterprise.UpdateEnterpriseServiceImpl;
import com.NTTDATA.bootcamp.msvc_customer.application.usecase.personal.CreatePersonalServiceImpl;
import com.NTTDATA.bootcamp.msvc_customer.application.usecase.personal.DesactivatePersonalServiceImpl;
import com.NTTDATA.bootcamp.msvc_customer.application.usecase.personal.RetrivePersonalServiceImpl;
import com.NTTDATA.bootcamp.msvc_customer.application.usecase.personal.UpdatePersonalServiceImpl;
import com.NTTDATA.bootcamp.msvc_customer.application.usecase.validation.CustomerValidationServiceImpl;
import com.NTTDATA.bootcamp.msvc_customer.domain.port.out.ICustomerRepositoryPort;
import com.NTTDATA.bootcamp.msvc_customer.domain.port.out.IEnterpriseRepositoryPort;
import com.NTTDATA.bootcamp.msvc_customer.domain.port.out.IPersonalRepositoryPort;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UseCaseBeanConfig {

    @Bean
    ICreatePersonalUseCase createPersonalCustomerUseCase(IPersonalRepositoryPort personalRepositoryPort) {
        return new CreatePersonalServiceImpl(personalRepositoryPort);
    }

    @Bean
    ICreateEnterpriseUseCase createEnterpriseCustomerUseCase(IEnterpriseRepositoryPort enterpriseRepositoryPort) {
        return new CreateEnterpriseServiceImpl(enterpriseRepositoryPort);
    }

    @Bean
    IRetrivePersonalUseCase retrivePersonalUseCase(IPersonalRepositoryPort personalRepositoryPort) {
        return new RetrivePersonalServiceImpl(personalRepositoryPort);
    }

    @Bean
    IRetriveEnterpriseUseCase retriveEnterpriseUseCase(IEnterpriseRepositoryPort enterpriseRepositoryPort) {
        return new RetriveEnterpriseServiceImpl(enterpriseRepositoryPort);
    }

    @Bean
    IDesactivatePersonalUseCase deactivatePersonalUseCase(IPersonalRepositoryPort personalRepositoryPort) {
        return new DesactivatePersonalServiceImpl(personalRepositoryPort);
    }

    @Bean
    IDesactivateEnterpriseUseCase deactivateEnterpriseUseCase(IEnterpriseRepositoryPort enterpriseRepositoryPort) {
        return new DesactivateEnterpriseServiceImpl(enterpriseRepositoryPort);
    }

    @Bean
    IUpdatePersonalUseCase updatePersonalUseCase(IPersonalRepositoryPort personalRepositoryPort) {
        return new UpdatePersonalServiceImpl(personalRepositoryPort);
    }

    @Bean
    IUpdateEnterpriseUseCase updateEnterpriseUseCase(IEnterpriseRepositoryPort enterpriseRepositoryPort) {
        return new UpdateEnterpriseServiceImpl(enterpriseRepositoryPort);
    }

    @Bean
    ICustomerValidationUseCase customerValidationUseCase(ICustomerRepositoryPort customerRepositoryPort) {
        return new CustomerValidationServiceImpl(customerRepositoryPort);
    }

}
