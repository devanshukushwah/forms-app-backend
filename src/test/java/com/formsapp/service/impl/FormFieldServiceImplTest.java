package com.formsapp.service.impl;

import com.formsapp.exception.FormException;
import com.formsapp.model.FormField;
import com.formsapp.repository.FormFieldRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class FormFieldServiceImplTest {

    @Mock
    private FormFieldRepository formFieldRepository;

    @InjectMocks
    private FormFieldServiceImpl formFieldService;

    @Test
    public void saveFormFieldTest() throws FormException {
        // given
        String formId = "1";
        FormField formField = new FormField();
        formField.setFormId(formId);

        // when
        Mockito.when(formFieldRepository.save(formField)).thenReturn(formField);

        // then
        FormField save = formFieldService.save(formId, formField);
        assertNotNull(save);
        assertEquals(formField, save);
    }
}