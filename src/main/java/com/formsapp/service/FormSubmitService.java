package com.formsapp.service;

import com.formsapp.model.FormSubmit;

import java.util.UUID;

public interface FormSubmitService {
    Boolean addSubmit(FormSubmit formSubmit);
    FormSubmit getSubmit(UUID formId, String email);
}
