package com.formsapp.service;

import com.formsapp.dto.SubmitDTO;
import com.formsapp.entity.Submit;

public interface FormSubmitKafkaService {

    /**
     * Adds a new form submission.
     *
     * @param formSubmit the {@link Submit} entity containing the form submission data
     * @return {@code true} if the submission was successfully added, {@code false} otherwise
     */
    Boolean addSubmit(SubmitDTO submitDto);
}
