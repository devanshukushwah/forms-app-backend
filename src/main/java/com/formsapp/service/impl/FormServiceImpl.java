package com.formsapp.service.impl;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.formsapp.model.Form;
import com.formsapp.model.FormFieldInput;
import com.formsapp.repository.FormFieldInputRepository;
import com.formsapp.repository.FormRepository;
import com.formsapp.service.FormService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

@Service
public class FormServiceImpl implements FormService {

    @Autowired
    private FormRepository formRepository;

    @Autowired
    private FormFieldInputRepository formFieldInputRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public Form getForm(UUID uuid) {
        Form form = formRepository.getReferenceById(uuid);
        form.setFormFieldList(getFormField(uuid));
        return form;
    }

    private List<HashMap<String, ?>> getFormField(UUID uuid) {
        List<HashMap<String, ?>> list = new ArrayList<>();
        List<FormFieldInput> formFieldInputList = formFieldInputRepository.findByFormId(uuid);
        formFieldInputList.forEach((item) -> {
            HashMap hashMap = objectMapper.convertValue(item, HashMap.class);
            list.add(hashMap);
        });
        return list;
    }

    @Transactional
    @Override
    public UUID addForm(Form form) {
        Form save = formRepository.save(form);
        if (save.getId() == null) {
            return null;
        }
        addFormField(form.getId(), form.getFormFieldList());
        return save.getId();
    }

    private void addFormField(UUID uuid, List<HashMap<String, ?>> formFieldList) {
        for (HashMap<String, ?> formField: formFieldList) {
            if (formField.containsKey("type") && formField.get("type").toString().equals("input")) {
                FormFieldInput formFieldInput = objectMapper.convertValue(formField, FormFieldInput.class);
                formFieldInput.setFormId(uuid);
                formFieldInputRepository.save(formFieldInput);
            }
        }
    }

    @Override
    public Form updateForm(Form form) {
        return null;
    }
}
