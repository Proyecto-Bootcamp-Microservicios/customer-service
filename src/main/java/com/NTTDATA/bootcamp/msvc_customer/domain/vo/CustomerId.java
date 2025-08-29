package com.NTTDATA.bootcamp.msvc_customer.domain.vo;

import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.util.Objects;

@Getter
@EqualsAndHashCode(of = "value")
public final class CustomerId {

    private final String value;

    private CustomerId(String value) {
        if (value == null || value.trim().isEmpty()) throw new IllegalArgumentException("Customer ID cannot be null or empty");
        this.value = value;
    }

    public static CustomerId of(String value) {
        return new CustomerId(value);
    }

}
