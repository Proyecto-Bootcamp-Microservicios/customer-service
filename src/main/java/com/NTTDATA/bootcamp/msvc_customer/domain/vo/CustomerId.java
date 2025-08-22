package com.NTTDATA.bootcamp.msvc_customer.domain.vo;

import lombok.Getter;

import java.util.Objects;

@Getter
public final class CustomerId {

    private final String value;

    public CustomerId(String value) {
        if (value == null || value.trim().isEmpty()) throw new IllegalArgumentException("Customer ID cannot be null or empty");
        this.value = value;
    }

    public static CustomerId create(String value) {
        return new CustomerId(value);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        CustomerId that = (CustomerId) obj;
        return Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
