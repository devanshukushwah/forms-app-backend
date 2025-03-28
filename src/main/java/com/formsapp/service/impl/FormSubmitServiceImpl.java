package com.formsapp.service.impl;

import com.formsapp.dto.FormAndSubmitDTO;
import com.formsapp.dto.FormDTO;
import com.formsapp.dto.ResponseDTO;
import com.formsapp.dto.SubmitDTO;
import com.formsapp.entity.FormSubmit;
import com.formsapp.entity.projection.Submission;
import com.formsapp.mapper.SubmissionMapper;
import com.formsapp.mapper.SubmitMapper;
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
import java.util.Optional;
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
        FormSubmit formSubmit = SubmitMapper.dtoToEntity(submitDto);
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
    public SubmitDTO getLastSubmit(String formId, String email) {
        Optional<FormSubmit> formSubmit = formSubmitRepository.findTopByFormIdAndEmailOrderBySubIdDesc(formId, email);
        return formSubmit.map(SubmitMapper::entityToDto).orElse(null);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public SubmitDTO getSubmit(UUID subId) {
        FormSubmit formSubmit = formSubmitRepository.findBySubId(subId);
        return SubmitMapper.entityToDto(formSubmit);
    }

    @Override
    public FormAndSubmitDTO getFormAndSubmit(UUID subId) {
        FormSubmit formSubmit = formSubmitRepository.findBySubId(subId);
        if (formSubmit != null) {
            FormDTO form = formService.getForm(formSubmit.getFormId());
            return FormAndSubmitDTO.builder()
                    .submit(SubmitMapper.entityToDto(formSubmit))
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
