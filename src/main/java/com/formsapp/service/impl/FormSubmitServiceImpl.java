package com.formsapp.service.impl;

import com.formsapp.dto.*;
import com.formsapp.entity.Submit;
import com.formsapp.entity.projection.Submission;
import com.formsapp.mapper.SubmissionMapper;
import com.formsapp.mapper.SubmitMapper;
import com.formsapp.repository.FormRepository;
import com.formsapp.repository.FormSubmitRepository;
import com.formsapp.service.FormService;
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

    @Autowired
    private FormService formService;

    /**
     * {@inheritDoc}
     */
    @Override
    public Boolean addSubmit(SubmitDTO submitDto) {
        Submit submit = SubmitMapper.dtoToEntity(submitDto);
        if (submit.getAnswers() != null) {
            submit.getAnswers().forEach((answer) -> answer.setSubmit(submit));
        }
        submit.setCreatedDate(new Date()); // set date.
        Submit save = formSubmitRepository.save(submit);
        return save.getSubId() != null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public SubmitDTO getSubmit(String formId, String email) {
        Submit submit = formSubmitRepository.findByFormIdAndEmail(formId, email);
        return SubmitMapper.entityToDto(submit);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public SubmitDTO getSubmit(UUID subId) {
        Submit submit = formSubmitRepository.findBySubId(subId);
        return SubmitMapper.entityToDto(submit);
    }

    @Override
    public FormAndSubmitDTO getFormAndSubmit(UUID subId) {
        Submit submit = formSubmitRepository.findBySubId(subId);
        if (submit != null) {
            FormDTO form = formService.getForm(submit.getFormId());
            return FormAndSubmitDTO.builder()
                    .submit(SubmitMapper.entityToDto(submit))
                    .form(form).build();
        }
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ResponseDTO getResponses(String formId, int page, int size, String sortField, String sortOrder) {
        // Create Sort object based on field and direction
        Sort sort = Sort.by(Sort.Order.by(sortField));
        if ("desc".equalsIgnoreCase(sortOrder)) {
            sort = sort.descending();
        } else {
            sort = sort.ascending();
        }

        // Create Pageable object with page number, page size, and sort order
        Pageable pageable = PageRequest.of(page, size, sort);

        Page<Submission> responses = formSubmitRepository.findAllByFormId(formId, pageable);
        return ResponseDTO.builder().responses(responses.map(SubmissionMapper::entityToDto)).build();
    }
}
