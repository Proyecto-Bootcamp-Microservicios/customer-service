package com.NTTDATA.bootcamp.msvc_customer.domain.vo;

import com.NTTDATA.bootcamp.msvc_customer.domain.enums.DocumentType;
import lombok.Getter;

@Getter
public final class IdentificationDocument {

    private final String value;
    private final DocumentType type;

    private IdentificationDocument(String value, DocumentType type) {
        if (value == null || value.trim().isEmpty()) throw new IllegalArgumentException("Document number cannot be null or empty");
        if (type == null) throw new IllegalArgumentException("Document type cannot be null");
        this.value = value;
        this.type = type;
    }

    public static IdentificationDocument of(String value, DocumentType type) {
        return new IdentificationDocument(value, type);
    }

}
