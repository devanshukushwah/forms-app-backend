package com.formsapp.service;

import com.formsapp.exception.FormException;
import com.formsapp.model.FormField;

public interface FormFieldService {
    FormField save(String formId, FormField formField) throws FormException;
    FormField updateFormField(String formId, Long formFieldId, FormField formField) throws FormException;
}
