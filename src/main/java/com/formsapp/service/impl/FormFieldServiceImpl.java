package com.formsapp.service.impl;

import com.formsapp.exception.FormException;
import com.formsapp.model.FormField;
import com.formsapp.repository.FormFieldRepository;
import com.formsapp.service.FormFieldService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FormFieldServiceImpl implements FormFieldService {

    @Autowired
    private FormFieldRepository formFieldRepository;

    @Override
    public FormField save(String formId, FormField formField) throws FormException {

        formField.setFormId(formId);

        // Ensure the bidirectional relationship is maintained
        if (formField.getAttributes() != null) {
            formField.getAttributes().forEach(attribute -> attribute.setFormField(formField));
        }

        return formFieldRepository.save(formField);
    }

    @Override
    public FormField updateFormField(String formId, Long formFieldId, FormField formField) throws FormException {
        formField.setFieldId(formFieldId);
        return save(formId, formField);
    }
}
