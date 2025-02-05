package com.formsapp.service.impl;

import com.formsapp.dto.FormDTO;
import com.formsapp.entity.Form;
import com.formsapp.entity.FormField;
import com.formsapp.repository.FormFieldRepository;
import com.formsapp.repository.FormRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class FormServiceImplTest {

    @Mock
    private FormRepository formRepository;

    @Mock
    private FormFieldRepository formFieldRepository;

    @InjectMocks
    private FormServiceImpl formService;

    @Test
    public void addFormTest() {
        // given
        String formId = "1";
        Form form = new Form();
        List<FormField> formFields = new ArrayList<>();
        formFields.add(new FormField());

        // when
        Mockito.when(formRepository.findByFormId(formId)).thenReturn(form);
        Mockito.when(formFieldRepository.findByFormId(formId)).thenReturn(formFields);

        // then
        FormDTO result = formService.getForm(formId);
        assertNotNull(result, "get form result is null");
        assertNotNull(result.getFormFields(), "form field is null");
        assertFalse(result.getFormFields().isEmpty(), "form field list is empty");

    }

}