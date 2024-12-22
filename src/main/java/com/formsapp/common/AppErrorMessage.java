package com.formsapp.common;

import lombok.Getter;

/**
 * Class to provide api messages for controller response.
 * */
@Getter
public enum AppErrorMessage {
    DATABASE_ERROR("database error"),
    DATA_IS_NOT_VALID("data is not valid");
    AppErrorMessage(String message) {
        this.message = message;
    }
    private final String message;
}
