package com.formsapp.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.formsapp.exception.FormException;
import com.formsapp.model.FormField;
import com.formsapp.repository.FormFieldRepository;
import com.formsapp.service.FormFieldService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class FormFieldServiceImpl implements FormFieldService {

    @Autowired
    private FormFieldRepository formFieldRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public FormField save(UUID formId, FormField formField) throws FormException {
        formField.setFormId(formId);
        return formFieldRepository.save(formField);
    }

    @Override
    public FormField updateFormField(UUID formId, Long formFieldId, FormField formField) throws FormException {
        formField.setId(formFieldId);
        return save(formId, formField);
    }
}
