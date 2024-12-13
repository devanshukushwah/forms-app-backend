package com.formsapp.service.impl;

import com.formsapp.exception.Operation;
import com.formsapp.model.Form;
import com.formsapp.repository.FormFieldRepository;
import com.formsapp.repository.FormRepository;
import com.formsapp.service.FormService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class FormServiceImpl implements FormService {

    @Autowired
    private FormRepository formRepository;

    @Autowired
    private FormFieldRepository formFieldRepository;

    @Override
    public Form getForm(UUID uuid) {
        Form form = formRepository.getReferenceById(uuid);
        form.setFormFields(formFieldRepository.findByFormId(uuid));
        return form;
    }

    @Override
    public UUID addForm(Form form) throws Operation {
        Form save = formRepository.save(form);
        if (save.getId() == null) {
            throw new Operation("failed to add form");
        }
        return save.getId();
    }

    @Override
    public Form updateForm(Form form) {
        return null;
    }
}
