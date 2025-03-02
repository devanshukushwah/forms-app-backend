package com.formsapp.controller;

import com.formsapp.common.AppMessage;
import com.formsapp.dto.SubmitDTO;
import com.formsapp.dto.core.CustomResponse;
import com.formsapp.exception.FormException;
import com.formsapp.service.FormSubmitService;
import com.formsapp.service.LoggedInUserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

/**
 * Controller for handling form submission-related requests.
 * <p>
 * This controller exposes API endpoints for fetching and submitting forms.
 * It interacts with the {@link FormSubmitService} to perform operations on form submissions.
 * </p>
 */
@Tag(name = "Form Submission Controller", description = "APIs for managing form submissions") // Swagger API categorization
@RestController
@RequestMapping("api/v1/submits")
public class FormSubmitController extends BaseController {

    @Autowired
    private FormSubmitService formSubmitService;

    @Autowired
    private LoggedInUserService loggedInUserService;

    /**
     * Fetches a form submission by its ID.
     *
     * @param subId The ID of the form submission.
     * @return A ResponseEntity containing the form submission data and a success/failure message.
     */
    @Operation(summary = "Get form submission by ID", description = "Retrieves the form submission using its unique ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Form submission retrieved successfully"),
            @ApiResponse(responseCode = "500", description = "Form submission not found")
    })
    @GetMapping(path = "{subId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CustomResponse<SubmitDTO>> getSubmit(
            @Parameter(description = "Unique ID of the form submission")
            @PathVariable("subId") UUID subId) {

        SubmitDTO formSubmit = formSubmitService.getSubmit(subId);
        if (formSubmit != null) {
            return responseOk(formSubmit); // Success response
        } else {
            return responseFailDataMessage(null, AppMessage.FORM_SUBMIT.getFetchFailed()); // Failure response
        }
    }

    /**
     * Adds a new form submission for a specific form.
     *
     * @param formId The ID of the form for which the submission is made.
     * @param submitDto The form submission object to be submitted.
     * @return A ResponseEntity containing the status of the form submission.
     */
    @Operation(summary = "Submit a form", description = "Submits a form for a given form ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Form submitted successfully"),
            @ApiResponse(responseCode = "500", description = "Form submission failed")
    })
    @PostMapping(path = "formId/{formId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CustomResponse<Boolean>> addSubmit(
            @Parameter(description = "ID of the form being submitted")
            @PathVariable("formId") String formId,
            @RequestBody SubmitDTO submitDto) throws FormException {

        submitDto.setEmail(loggedInUserService.getLoggedInUserEmail()); // Set logged-in user email
        submitDto.setFormId(formId);

        if (formSubmitService.addSubmit(submitDto)) {
            return responseOkDataMessage(true, AppMessage.FORM_SUBMIT.getSubmitSuccessfully()); // Success response
        } else {
            return responseFailDataMessage(false, AppMessage.FORM_SUBMIT.getSubmitFailed()); // Failure response
        }
    }

    /**
     * Fetches the last form submission by email.
     *
     * @param formId The ID of the form.
     * @param email The email address associated with the submission.
     * @return A ResponseEntity containing the form submission data.
     */
    @Operation(summary = "Get form submission by email", description = "Retrieves the most recent form submission for a given form and email.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Form submission retrieved successfully"),
            @ApiResponse(responseCode = "500", description = "Form submission not found")
    })
    @GetMapping(path = "formId/{formId}/email/{email}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CustomResponse<SubmitDTO>> getSubmitByEmail(
            @Parameter(description = "ID of the form")
            @PathVariable("formId") String formId,
            @Parameter(description = "Email associated with the submission")
            @PathVariable String email) {

        SubmitDTO formSubmit = formSubmitService.getLastSubmit(formId, email);
        if (formSubmit != null) {
            return responseOk(formSubmit); // Success response
        } else {
            return responseFailDataMessage(null, AppMessage.FORM_SUBMIT.getFetchFailed()); // Failure response
        }
    }

    /**
     * Fetches the last form submission for the logged-in user.
     *
     * @param formId The ID of the form.
     * @return A ResponseEntity containing the form submission data.
     */
    @Operation(summary = "Get form submission for logged-in user", description = "Retrieves the most recent form submission for a specific form based on the logged-in user's email.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Form submission retrieved successfully"),
            @ApiResponse(responseCode = "500", description = "Form submission not found")
    })
    @GetMapping(path = "formId/{formId}/email", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CustomResponse<SubmitDTO>> getSubmitByEmailThroughJWT(
            @Parameter(description = "ID of the form")
            @PathVariable("formId") String formId) throws FormException {

        SubmitDTO formSubmit = formSubmitService.getLastSubmit(formId, loggedInUserService.getLoggedInUserEmail());
        if (formSubmit != null) {
            return responseOk(formSubmit); // Success response
        } else {
            return responseFailDataMessage(null, AppMessage.FORM_SUBMIT.getFetchFailed()); // Failure response
        }
    }
}
