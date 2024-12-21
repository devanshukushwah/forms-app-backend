package com.formsapp.service;

import com.formsapp.exception.FormException;
import com.formsapp.model.FormField;
import com.formsapp.model.FormFieldAttribute;

import java.util.List;
import java.util.UUID;

public interface FormFieldService {
    FormField save(String formId, FormField formField) throws FormException;
    FormField updateFormField(String formId, Long formFieldId, FormField formField) throws FormException;
}
