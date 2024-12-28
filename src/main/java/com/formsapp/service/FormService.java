package com.formsapp.service;

import com.formsapp.exception.Operation;
import com.formsapp.model.Form;
import org.springframework.data.domain.Page;

import java.util.List;

public interface FormService {
    Form getForm(String formId);
    Page<Form> getAllForm(int page, int size, String sortField, String sortOrder);
    String addForm(Form form) throws Operation;
    Form updateForm(String formId, Form form) throws Operation;
}
