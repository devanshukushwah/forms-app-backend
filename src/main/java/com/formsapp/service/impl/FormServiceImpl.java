package com.formsapp.service.impl;

import com.formsapp.model.Form;
import com.formsapp.repository.FormRepository;
import com.formsapp.service.FormService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class FormServiceImpl implements FormService {

    @Autowired
    private FormRepository formRepository;

    @Override
    public Form getForm(UUID uuid) {
        return formRepository.getReferenceById(uuid);
    }

    @Override
    public UUID addForm(Form form) {
        Form save = formRepository.save(form);
        return save.getId();
    }
}
