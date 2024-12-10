package com.formsapp.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.formsapp.common.Attribute;
import com.formsapp.exception.InvalidFormFieldType;
import com.formsapp.exception.Operation;
import com.formsapp.common.FormFieldType;
import com.formsapp.model.BaseModel;
import com.formsapp.model.Form;
import com.formsapp.model.FormFieldInput;
import com.formsapp.repository.FormFieldInputRepository;
import com.formsapp.repository.FormRepository;
import com.formsapp.service.FormService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

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

    private List<BaseModel> getFormField(UUID uuid) {
        List<BaseModel> list = new ArrayList<>();
        List<FormFieldInput> formFieldInputList = formFieldInputRepository.findByFormId(uuid);
        formFieldInputList.forEach((item) -> {
            list.add(objectMapper.convertValue(item, BaseModel.class));
        });
        return list;
    }

    @Transactional
    @Override
    public UUID addForm(Form form) throws Operation, InvalidFormFieldType {
        Form save = formRepository.save(form);
        if (save.getId() == null) {
            throw new Operation("failed to add form");
        }
        addFormField(save.getId(), form.getFormFieldList());
        return save.getId();
    }

    private void addFormField(UUID uuid, List<BaseModel> formFieldList) throws InvalidFormFieldType, Operation {
        for (BaseModel formField: formFieldList) {
            String type =  formField.getType();
            if (type == null) {
                throw new Operation("type not found");
            }
            if (FormFieldType.INPUT.equalsIgnoreCase(type)) {
                FormFieldInput formFieldInput = objectMapper.convertValue(formField, FormFieldInput.class);
                formFieldInput.setFormId(uuid);
                formFieldInputRepository.save(formFieldInput);
            } else {
                throw new InvalidFormFieldType(type);
            }
        }
    }

    @Override
    public Form updateForm(Form form) {
        return null;
    }
}
