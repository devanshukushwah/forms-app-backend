package com.formsapp.service.impl;

import com.formsapp.model.FormSubmit;
import com.formsapp.model.projection.FormResponse;
import com.formsapp.repository.FormSubmitRepository;
import com.formsapp.service.FormSubmitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.UUID;

@Service
public class FormSubmitServiceImpl implements FormSubmitService {

    @Autowired
    private FormSubmitRepository formSubmitRepository;

    /**
     * {@inheritDoc}
     */
    @Override
    public Boolean addSubmit(FormSubmit formSubmit) {
        if (formSubmit.getAnswers() != null) {
            formSubmit.getAnswers().forEach((answer) -> answer.setFormSubmit(formSubmit));
        }
        formSubmit.setCreatedDate(new Date()); // set date.
        FormSubmit save = formSubmitRepository.save(formSubmit);
        return save.getSubId() != null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public FormSubmit getSubmit(String formId, String email) {
        return formSubmitRepository.findByFormIdAndEmail(formId, email);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public FormSubmit getSubmit(UUID subId) {
        return formSubmitRepository.findBySubId(subId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Page<FormResponse> getResponses(String formId, int page, int size, String sortField, String sortOrder) {
        // Create Sort object based on field and direction
        Sort sort = Sort.by(Sort.Order.by(sortField));
        if ("desc".equalsIgnoreCase(sortOrder)) {
            sort = sort.descending();
        } else {
            sort = sort.ascending();
        }

        // Create Pageable object with page number, page size, and sort order
        Pageable pageable = PageRequest.of(page, size, sort);

        return formSubmitRepository.findAllByFormId(formId, pageable);
    }
}
