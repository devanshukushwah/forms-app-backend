package com.formsapp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * A data structure that holds both a form and its associated form submission.
 * <p>
 * This class is used to combine the form details and the form submission data into a single object
 * for easier transfer or processing.
 * </p>
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FormAndSubmit {

    /**
     * The form associated with the submission.
     */
    private Form form;

    /**
     * The form submission data.
     */
    private FormSubmit submit;
}
