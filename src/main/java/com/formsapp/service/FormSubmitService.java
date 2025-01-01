package com.formsapp.service;

import com.formsapp.model.FormSubmit;
import com.formsapp.model.projection.FormResponse;

import java.util.List;

/**
 * Service interface for managing form submissions.
 * <p>
 * This interface defines methods for handling form submissions, including adding a new submission,
 * retrieving submissions by form ID or submission ID, and retrieving form responses.
 * </p>
 */
public interface FormSubmitService {

    /**
     * Adds a new form submission.
     *
     * @param formSubmit the {@link FormSubmit} entity containing the form submission data
     * @return {@code true} if the submission was successfully added, {@code false} otherwise
     */
    Boolean addSubmit(FormSubmit formSubmit);

    /**
     * Retrieves a form submission by form ID and email.
     *
     * @param formId the ID of the form
     * @param email the email associated with the form submission
     * @return the {@link FormSubmit} entity for the corresponding form and email, or {@code null} if not found
     */
    FormSubmit getSubmit(String formId, String email);

    /**
     * Retrieves a form submission by submission ID.
     *
     * @param subId the ID of the form submission
     * @return the {@link FormSubmit} entity for the corresponding submission ID, or {@code null} if not found
     */
    FormSubmit getSubmit(Long subId);

    /**
     * Retrieves all responses for a specific form.
     *
     * @param formId the ID of the form to retrieve responses for
     * @return a list of {@link FormResponse} projections containing form responses
     */
    List<FormResponse> getResponses(String formId);
}
