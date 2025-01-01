package com.formsapp.controller;

import com.formsapp.common.AppMessage;
import com.formsapp.model.FormSubmit;
import com.formsapp.model.core.CustomResponse;
import com.formsapp.service.FormSubmitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Controller for handling form submission-related requests.
 * <p>
 * This controller exposes API endpoints for fetching and submitting forms.
 * It interacts with the {@link FormSubmitService} to perform operations on form submissions.
 * </p>
 */
@RestController
@RequestMapping("api/v1/submits")
public class FormSubmitController extends BaseController {

    @Autowired
    private FormSubmitService formSubmitService;

    /**
     * Fetches a form submission by its ID.
     * <p>
     * This method retrieves the details of a form submission identified by the provided submission ID.
     * If the submission is found, it is returned along with a success response. Otherwise, a failure message is returned.
     * </p>
     *
     * @param subId The ID of the form submission.
     * @return A {@link ResponseEntity} containing a {@link CustomResponse} with the form submission data and a success/failure message.
     */
    @RequestMapping(method = RequestMethod.GET, path = "{subId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CustomResponse<FormSubmit>> getSubmit(@PathVariable("subId") Long subId) {
        FormSubmit formSubmit = formSubmitService.getSubmit(subId);
        if(formSubmit != null) {
            return responseOk(formSubmit);
        } else {
            return responseFailDataMessage(null, AppMessage.FORM_SUBMIT.getFetchFailed());
        }
    }

    /**
     * Adds a new form submission for a specific form.
     * <p>
     * This method accepts a {@link FormSubmit} object in the request body and submits it for the specified form.
     * If the submission is successful, a success message is returned. If the submission fails, a failure message is returned.
     * </p>
     *
     * @param formId The ID of the form for which the submission is made.
     * @param formSubmit The form submission object to be submitted.
     * @return A {@link ResponseEntity} containing a {@link CustomResponse} with the status of the form submission.
     */
    @RequestMapping(method = RequestMethod.POST, path = "formId/{formId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CustomResponse<Boolean>> addSubmit(@PathVariable("formId") String formId, @RequestBody FormSubmit formSubmit) {
        Boolean res = formSubmitService.addSubmit(formSubmit);
        if(res) {
            return responseOkDataMessage(true, AppMessage.FORM_SUBMIT.getSubmitSuccessfully());
        } else {
            return responseFailDataMessage(false, AppMessage.FORM_SUBMIT.getSubmitFailed());
        }
    }

    /**
     * Fetches a form submission by the email address.
     * <p>
     * This method retrieves the form submission associated with the given email address for a specific form.
     * If the submission is found, it is returned along with a success response. Otherwise, a failure message is returned.
     * </p>
     *
     * @param formId The ID of the form to fetch submissions for.
     * @param email The email address associated with the submission.
     * @return A {@link ResponseEntity} containing a {@link CustomResponse} with the form submission data and a success/failure message.
     */
    @RequestMapping(method = RequestMethod.GET, path = "email/{email}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CustomResponse<FormSubmit>> getSubmitByEmail(@PathVariable("formId") String formId, @PathVariable String email) {
        FormSubmit formSubmit = formSubmitService.getSubmit(formId, email);
        if(formSubmit != null) {
            return responseOk(formSubmit);
        } else {
            return responseFailDataMessage(null, AppMessage.FORM_SUBMIT.getFetchFailed());
        }
    }

}
