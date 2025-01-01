package com.formsapp.service;

import com.formsapp.exception.FormException;
import com.formsapp.model.FormField;

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
    FormField save(String formId, FormField formField) throws FormException;

    /**
     * Updates an existing form field associated with a specific form.
     *
     * @param formId the ID of the form the field belongs to
     * @param formFieldId the ID of the field to be updated
     * @param formField the updated {@link FormField} entity
     * @return the updated {@link FormField} entity
     * @throws FormException if an error occurs during the update operation
     */
    FormField updateFormField(String formId, Long formFieldId, FormField formField) throws FormException;
}
