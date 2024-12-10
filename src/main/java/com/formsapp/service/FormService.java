package com.formsapp.service;

import com.formsapp.model.Form;

import java.util.UUID;

public interface FormService {
    Form getForm(UUID uuid);
    UUID addForm(Form form);
    Form updateForm(Form form);
}
