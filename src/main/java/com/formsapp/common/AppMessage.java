package com.formsapp.common;

import lombok.Getter;
/**
 * Class to provide api messages for controller response.
 * */
@Getter
public enum AppMessage {
    FORM("form"),
    FORM_FIELD("form field"),
    FORM_SUBMIT("form submit");
    AppMessage(String message) {
        this.message = message;
        this.createSuccessfully = message.concat(SPACE).concat(CREATE).concat(SPACE).concat(SUCCESSFULLY);
        this.submitSuccessfully = message.concat(SPACE).concat(SUBMIT).concat(SPACE).concat(SUCCESSFULLY);
        this.updateSuccessfully = message.concat(SPACE).concat(UPDATE).concat(SPACE).concat(SUCCESSFULLY);
        this.createFailed = FAILED_TO.concat(SPACE).concat(CREATE).concat(SPACE).concat(message);
        this.submitFailed = FAILED_TO.concat(SPACE).concat(SUBMIT).concat(SPACE).concat(message);
        this.updateFailed = FAILED_TO.concat(SPACE).concat(UPDATE).concat(SPACE).concat(message);
        this.fetchFailed = FAILED_TO.concat(SPACE).concat(FETCH).concat(SPACE).concat(message);
    }
    private final static String SUCCESSFULLY = "successfully";
    private final static String FAILED_TO = "failed to";
    private final static String CREATE = "create";
    private final static String UPDATE = "update";
    private final static String FETCH = "fetch";
    private final static String SUBMIT = "submit";
    private final static String SPACE = " ";
    private final String message;
    private final String createSuccessfully;
    private final String submitSuccessfully;
    private final String updateSuccessfully;
    private final String createFailed;
    private final String submitFailed;
    private final String updateFailed;
    private final String fetchFailed;
}
