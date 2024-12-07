package com.formsapp.common;

import lombok.Getter;

/**
 * Class to provide api messages for controller response.
 * */
@Getter
public enum AppMessage {
    FORM_CREATED_SUCCESSFULLY("form created successfully"),
    FORM_UPDATED_SUCCESSFULLY("form updated successfully");
    AppMessage(String message) {
        this.message = message;
    }
    private final String message;
}
