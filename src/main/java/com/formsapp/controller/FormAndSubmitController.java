package com.formsapp.controller;

import com.formsapp.common.AppErrorMessage;
import com.formsapp.dto.FormAndSubmitDTO;
import com.formsapp.dto.core.CustomResponse;
import com.formsapp.exception.FormException;
import com.formsapp.service.FormService;
import com.formsapp.service.FormSubmitService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

/**
 * Controller for handling form submission related requests.
 * <p>
 * This controller provides an API to retrieve form submission details along with the form itself.
 * It interacts with {@link FormSubmitService} to fetch submissions and {@link FormService} to fetch form details.
 * </p>
 */
@Slf4j // Enables logging
@Tag(name = "Form Submission", description = "APIs for retrieving form submission details") // Swagger API category
@RestController
@RequestMapping("api/v1/form-submit")
public class FormAndSubmitController extends BaseController {

    @Autowired
    private FormSubmitService formSubmitService;

    @Autowired
    private FormService formService;

    /**
     * Retrieves the details of a specific form submission by its submission ID.
     *
     * @param subId The unique ID of the form submission.
     * @return A {@link ResponseEntity} containing a {@link CustomResponse} with form and submission details.
     * @throws FormException If the form submission or associated form cannot be retrieved.
     */
    @Operation(summary = "Get form submission details", description = "Fetches a form submission along with its associated form details.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved form submission details"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping(path = "{subId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CustomResponse<FormAndSubmitDTO>> getFormAndSubmit(@PathVariable(name = "subId") UUID subId) throws FormException {
        log.info("Fetching form submission details for subId: {}", subId);

        FormAndSubmitDTO formAndSubmit = formSubmitService.getFormAndSubmit(subId);
        if (formAndSubmit == null) {
            log.warn("Form submission not found for subId: {}", subId);
            throw new FormException(AppErrorMessage.GET_FORM_SUBMIT_FAILED.getMessage());
        }

        return responseOk(formAndSubmit);
    }
}
