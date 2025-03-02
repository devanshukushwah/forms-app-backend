package com.formsapp.service;

import com.formsapp.dto.FormFieldDTO;
import com.formsapp.exception.FormException;
import com.formsapp.entity.FormField;

/**
 * Service interface for handling form field operations.
 * <p>
 * This interface defines methods for saving and updating form fields associated with specific forms.
 * </p>
 */
public interface FormFieldService {

    /**
     * Saves a new form field for a specific form.
     *
     * @param formId the ID of the form to associate the field with
     * @param formField the {@link FormField} entity to be saved
     * @return the saved {@link FormField} entity
     * @throws FormException if an error occurs during the save operation
     */
    FormFieldDTO save(String formId, FormFieldDTO formFieldDto) throws FormException;

    /**
     * Updates an existing form field associated with a specific form.
     *
     * @param formId the ID of the form the field belongs to
     * @param formFieldId the ID of the field to be updated
     * @param formField the updated {@link FormField} entity
     * @return the updated {@link FormField} entity
     * @throws FormException if an error occurs during the update operation
     */
    FormFieldDTO updateFormField(String formId, Long formFieldId, FormFieldDTO formFieldDto) throws FormException;


    /**
     * Delete form field associated with a specific form.
     *
     * @param formFieldId the ID of the field to be updated
     * @throws FormException if an error occurs during the update operation
     */
    Boolean deleteFormField(Long formFieldId) throws FormException;
}
