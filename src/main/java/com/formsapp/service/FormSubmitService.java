package com.formsapp.service;

import com.formsapp.model.FormSubmit;

import java.util.List;
import java.util.UUID;

public interface FormSubmitService {
    Boolean addSubmit(FormSubmit formSubmit);
    FormSubmit getSubmit(String formId, String email);
    List<FormSubmit> getResponses(String formId);
}
