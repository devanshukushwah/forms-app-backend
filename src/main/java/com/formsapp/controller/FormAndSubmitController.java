package com.formsapp.controller;

import com.formsapp.common.AppErrorMessage;
import com.formsapp.dto.FormDTO;
import com.formsapp.exception.FormException;
import com.formsapp.entity.Form;
import com.formsapp.entity.FormAndSubmit;
import com.formsapp.entity.FormSubmit;
import com.formsapp.entity.core.CustomResponse;
import com.formsapp.service.FormService;
import com.formsapp.service.FormSubmitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

/**
 * Controller for handling form submission related requests.
 * <p>
 * This controller exposes the endpoint for retrieving form submission data.
 * It handles requests to fetch a specific form submission, including the form details
 * associated with the submission.
 * </p>
 */
@RestController
@RequestMapping("api/v1/form-submit")
public class FormAndSubmitController extends BaseController {

    @Autowired
    private FormSubmitService formSubmitService;

    @Autowired
    private FormService formService;

    /**
     * Retrieves the details of a specific form submission by its submission ID.
     * <p>
     * This method fetches a form submission by its ID and, if applicable, includes
     * the associated form details. The response is wrapped in a {@link CustomResponse}.
     * If the submission or form is not found, a {@link FormException} is thrown.
     * </p>
     *
     * @param subId The ID of the form submission to be fetched.
     * @return A {@link ResponseEntity} containing the {@link CustomResponse} with the form and submission details.
     * @throws FormException If the form submission cannot be retrieved or if the associated form is not found.
     */
    @GetMapping(path = "{subId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CustomResponse<FormAndSubmit>> getResponse(@PathVariable(name = "subId") UUID subId) throws FormException {
        FormSubmit submit = formSubmitService.getSubmit(subId);
        if (submit != null) {
            FormAndSubmit formAndSubmit = new FormAndSubmit();
            formAndSubmit.setSubmit(submit);
            if (submit.getFormId() != null) {
                FormDTO formDto = formService.getForm(submit.getFormId());
                formAndSubmit.setForm(formDto);
            }
            return responseOk(formAndSubmit);
        }

        throw new FormException(AppErrorMessage.GET_FORM_SUBMIT_FAILED.getMessage());
    }
}
