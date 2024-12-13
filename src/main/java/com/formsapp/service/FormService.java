package com.formsapp.service;

import com.formsapp.exception.Operation;
import com.formsapp.model.Form;

import java.util.UUID;

public interface FormService {
    Form getForm(UUID uuid);
    UUID addForm(Form form) throws Operation, InvalidFormFieldType;
    Form updateForm(Form form);
}
