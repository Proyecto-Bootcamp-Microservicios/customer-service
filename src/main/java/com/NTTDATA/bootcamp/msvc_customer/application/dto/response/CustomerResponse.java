package com.NTTDATA.bootcamp.msvc_customer.application.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CustomerResponse {
    private String id;
    private String name;
    private String customerType;
    private String documentNumber;
    private String email;
    private boolean active;
    private LocalDateTime createdAt;
}
