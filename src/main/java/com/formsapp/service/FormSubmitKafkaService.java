package com.formsapp.service;

import com.formsapp.entity.FormSubmit;

public interface FormSubmitKafkaService {

    /**
     * Adds a new form submission.
     *
     * @param formSubmit the {@link FormSubmit} entity containing the form submission data
     * @return {@code true} if the submission was successfully added, {@code false} otherwise
     */
    Boolean addSubmit(FormSubmit formSubmit);
}
