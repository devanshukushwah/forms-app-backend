package com.formsapp.common;

import lombok.Getter;
import org.springframework.util.StringUtils;

@Getter
public enum FormFieldType {
    INPUT("input");
    FormFieldType(String value) {
        this.value = value;
    }
    private final String value;

    public boolean equalsIgnoreCase(String value) {
        return this.value.equalsIgnoreCase(value);
    }
}
