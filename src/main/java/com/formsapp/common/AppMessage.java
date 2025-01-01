package com.formsapp.common;

import lombok.Getter;

/**
 * Enum to provide standardized API messages for controller responses.
 * <p>
 * This enum defines a set of predefined messages for various operations like creation,
 * submission, updating, and fetching. These messages help ensure consistency in response
 * messaging across the application.
 * </p>
 */
@Getter
public enum AppMessage {

    /**
     * Represents messages related to "form" operations.
     */
    FORM("form"),

    /**
     * Represents messages related to "form field" operations.
     */
    FORM_FIELD("form field"),

    /**
     * Represents messages related to "form submit" operations.
     */
    FORM_SUBMIT("form submit");

    /**
     * Constructor to initialize the base message and various derived messages for operations.
     *
     * @param message The base message associated with the enum constant.
     */
    AppMessage(String message) {
        this.message = message;
        this.createSuccessfully = message.concat(SPACE).concat(CREATE).concat(SPACE).concat(SUCCESSFULLY);
        this.submitSuccessfully = message.concat(SPACE).concat(SUBMIT).concat(SPACE).concat(SUCCESSFULLY);
        this.updateSuccessfully = message.concat(SPACE).concat(UPDATE).concat(SPACE).concat(SUCCESSFULLY);
        this.createFailed = FAILED_TO.concat(SPACE).concat(CREATE).concat(SPACE).concat(message);
        this.submitFailed = FAILED_TO.concat(SPACE).concat(SUBMIT).concat(SPACE).concat(message);
        this.updateFailed = FAILED_TO.concat(SPACE).concat(UPDATE).concat(SPACE).concat(message);
        this.fetchFailed = FAILED_TO.concat(SPACE).concat(FETCH).concat(SPACE).concat(message);
        this.notFound = message.concat(SPACE).concat(NOT_FOUND);
    }

    // Static constants used to construct operation-specific messages.
    private static final String SUCCESSFULLY = "successfully";
    private static final String FAILED_TO = "failed to";
    private static final String CREATE = "create";
    private static final String UPDATE = "update";
    private static final String FETCH = "fetch";
    private static final String SUBMIT = "submit";
    private static final String SPACE = " ";
    private static final String NOT_FOUND = "not found";

    /**
     * The base message associated with the enum constant.
     */
    private final String message;

    /**
     * Message for successful creation operation.
     */
    private final String createSuccessfully;

    /**
     * Message for successful submission operation.
     */
    private final String submitSuccessfully;

    /**
     * Message for successful update operation.
     */
    private final String updateSuccessfully;

    /**
     * Message for failed creation operation.
     */
    private final String createFailed;

    /**
     * Message for failed submission operation.
     */
    private final String submitFailed;

    /**
     * Message for failed update operation.
     */
    private final String updateFailed;

    /**
     * Message for failed fetch operation.
     */
    private final String fetchFailed;

    /**
     * Message indicating the resource was not found.
     */
    private final String notFound;
}
