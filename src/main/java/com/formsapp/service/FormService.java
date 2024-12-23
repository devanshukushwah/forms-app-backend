package com.formsapp.service;

import com.formsapp.exception.Operation;
import com.formsapp.model.Form;

import java.util.List;

public interface FormService {
    Form getForm(String formId);
    List<Form> getAllForm();
    String addForm(Form form) throws Operation;
    Form updateForm(Form form);
}
