package com.formsapp.common;

import lombok.Getter;

/**
 * Enum to provide standardized API error messages for controller responses.
 * <p>
 * This enum defines a set of predefined error messages that can be used in API responses
 * to ensure consistency and clarity in error communication.
 * </p>
 */
@Getter
public enum AppErrorMessage {

    /**
     * Represents a generic database error.
     * <p>
     * Example usage: When a database operation fails, this message can be used to
     * indicate the issue to the client.
     * </p>
     */
    DATABASE_ERROR("database error"),

    /**
     * Represents an error indicating that the provided data is invalid.
     * <p>
     * Example usage: When input validation fails for a given API request.
     * </p>
     */
    DATA_IS_NOT_VALID("data is not valid"),

    /**
     * Represents an error indicating the failure of form retrieval or submission.
     * <p>
     * Example usage: When fetching a form or submitting it fails due to server or data issues.
     * </p>
     */
    GET_FORM_SUBMIT_FAILED("failed to get form and submit");

    /**
     * Constructor to initialize the error message.
     *
     * @param message The error message associated with the error type.
     */
    AppErrorMessage(String message) {
        this.message = message;
    }

    /**
     * The error message associated with the enum constant.
     */
    private final String message;
}
