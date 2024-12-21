package com.formsapp.service.impl;

import com.formsapp.exception.Operation;
import com.formsapp.model.Form;
import com.formsapp.repository.FormFieldRepository;
import com.formsapp.repository.FormRepository;
import com.formsapp.service.FormService;
import com.formsapp.util.UUIDUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class FormServiceImpl implements FormService {

    @Autowired
    private FormRepository formRepository;

    @Autowired
    private FormFieldRepository formFieldRepository;

    @Override
    public Form getForm(String formId) {
        Form form = formRepository.findByFormId(formId);
        if (form != null) {
            form.setFormFields(formFieldRepository.findByFormId(formId));
        }
        return form;
    }

    /**
     * method to generate unique id for creating new form
     * @return generated form id
     * */
    private String generateFormId() throws Operation {
        try {
            String formId = UUIDUtils.generateUUID();
            while(formRepository.existsByFormId(formId)) {
                formId = UUIDUtils.generateUUID();
            }
            return formId;
        } catch (Exception ex) {
            log.error("failed to generate formId {} ", ex.getMessage(), ex);
        }
        throw new Operation("failed to add form");
    }

    @Override
    public String addForm(Form form) throws Operation {
        String formId = this.generateFormId();
        form.setFormId(formId);

        Form save = formRepository.save(form);
        if (save.getFormId() == null) {
            throw new Operation("failed to add form");
        }

        return save.getFormId();
    }

    @Override
    public Form updateForm(Form form) {
        return null;
    }
}
