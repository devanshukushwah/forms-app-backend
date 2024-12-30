package com.formsapp.service;

import com.formsapp.model.FormSubmit;
import com.formsapp.model.projection.FormResponse;

import java.util.List;

public interface FormSubmitService {
    Boolean addSubmit(FormSubmit formSubmit);
    FormSubmit getSubmit(String formId, String email);
    FormSubmit getSubmit(Long subId);
    List<FormResponse> getResponses(String formId);
}
