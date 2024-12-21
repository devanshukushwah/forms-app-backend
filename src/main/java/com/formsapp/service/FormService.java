package com.formsapp.service;

import com.formsapp.exception.Operation;
import com.formsapp.model.Form;

public interface FormService {
    Form getForm(String formId);
    String addForm(Form form) throws Operation;
    Form updateForm(Form form);
}
