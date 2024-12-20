package com.formsapp.service.impl;

import com.formsapp.model.FormSubmit;
import com.formsapp.repository.FormSubmitRepository;
import com.formsapp.service.FormSubmitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class FormSubmitServiceImpl implements FormSubmitService {

    @Autowired
    private FormSubmitRepository formSubmitRepository;

    @Override
    public Boolean addSubmit(FormSubmit formSubmit) {
        if (formSubmit.getAnswers() != null) {
           formSubmit.getAnswers().forEach((answer) -> answer.setFormSubmit(formSubmit));
        }
        FormSubmit save = formSubmitRepository.save(formSubmit);
        return save.getSubId() > 0;
    }

    @Override
    public FormSubmit getSubmit(UUID formId, String email) {
        return formSubmitRepository.findByFormIdAndEmail(formId, email);
    }
}
