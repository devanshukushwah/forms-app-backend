package com.formsapp.service;

import com.formsapp.dto.FormDTO;
import com.formsapp.exception.Operation;
import com.formsapp.entity.Form;
import org.springframework.data.domain.Page;

/**
 * Service interface for managing forms.
 * <p>
 * This interface defines methods for retrieving, adding, and updating forms in the system.
 * </p>
 */
public interface FormService {

    /**
     * Retrieves a form by its ID.
     *
     * @param formId the ID of the form to retrieve
     * @return the {@link Form} entity with the specified ID
     */
    FormDTO getForm(String formId);

    /**
     * Retrieves all forms with pagination and sorting.
     *
     * @param page the page number to retrieve
     * @param size the number of records per page
     * @param sortField the field by which to sort the results
     * @param sortOrder the order of sorting ("asc" for ascending, "desc" for descending)
     * @return a {@link Page} object containing the paginated forms
     */
    Page<FormDTO> getAllForm(int page, int size, String sortField, String sortOrder);

    /**
     * Retrieves all forms with pagination and sorting.
     *
     * @param createdBy user who created this form 
     * @param page the page number to retrieve
     * @param size the number of records per page
     * @param sortField the field by which to sort the results
     * @param sortOrder the order of sorting ("asc" for ascending, "desc" for descending)
     * @return a {@link Page} object containing the paginated forms
     */
    Page<FormDTO> getAllFormByCreatedBy(String createdBy, int page, int size, String sortField, String sortOrder);


    /**
     * Adds a new form to the system.
     *
     * @param form the {@link Form} entity to be added
     * @return the ID of the created form
     * @throws Operation if an error occurs during the form creation process
     */
    String addForm(FormDTO formDto) throws Operation;

    /**
     * Updates an existing form with new details.
     *
     * @param formId the ID of the form to update
     * @param form the updated {@link Form} entity
     * @return the updated {@link Form} entity
     * @throws Operation if an error occurs during the form update process
     */
    FormDTO updateForm(String formId, FormDTO form) throws Operation;
}
