package com.formsapp.service;

import com.formsapp.dto.FormAndSubmitDTO;
import com.formsapp.dto.ResponseDTO;
import com.formsapp.dto.SubmitDTO;
import com.formsapp.entity.FormSubmit;
import com.formsapp.entity.projection.Submission;
import org.springframework.data.domain.Page;

import java.util.UUID;

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
    Boolean addSubmit(SubmitDTO submitDto);

    /**
     * Retrieves a form submission by form ID and email.
     *
     * @param formId the ID of the form
     * @param email the email associated with the form submission
     * @return the {@link FormSubmit} entity for the corresponding form and email, or {@code null} if not found
     */
    SubmitDTO getSubmit(String formId, String email);

    /**
     * Retrieves a form submission by submission ID.
     *
     * @param subId the ID of the form submission
     * @return the {@link FormSubmit} entity for the corresponding submission ID, or {@code null} if not found
     */
    SubmitDTO getSubmit(UUID subId);

    /**
     * Retrieves a form submission by submission ID.
     *
     * @param subId the ID of the form submission
     * @return the {@link FormSubmit} entity for the corresponding submission ID, or {@code null} if not found
     */
    FormAndSubmitDTO getFormAndSubmit(UUID subId);


    /**
     * Retrieves all responses for a specific form.
     *
     * @param formId the ID of the form to retrieve responses for
     * @param page the page number to retrieve
     * @param size the number of responses per page
     * @param sortField the field to sort the responses by
     * @param sortOrder the order to sort the responses (e.g., "asc" or "desc")
     * @return a {@link Page} of {@link Submission} projections containing form responses
     */
    ResponseDTO getResponses(String formId, int page, int size, String sortField, String sortOrder);
}
