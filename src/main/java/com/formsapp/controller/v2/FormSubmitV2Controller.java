package com.formsapp.controller.v2;

import com.formsapp.common.AppMessage;
import com.formsapp.controller.BaseController;
import com.formsapp.dto.SubmitDTO;
import com.formsapp.dto.core.CustomResponse;
import com.formsapp.exception.FormException;
import com.formsapp.service.FormSubmitKafkaService;
import com.formsapp.service.LoggedInUserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Controller for handling form submissions using Kafka.
 * <p>
 * This controller exposes API endpoints for submitting forms asynchronously via Kafka.
 * It interacts with {@link FormSubmitKafkaService} for processing form submissions.
 * </p>
 */
@Slf4j // Enables logging
@Tag(name = "Form Submission Controller", description = "APIs for managing form submissions") // Swagger API categorization
@RestController
@RequestMapping("/api/v2/submits")
public class FormSubmitV2Controller extends BaseController {

    @Autowired
    private LoggedInUserService loggedInUserService;

    @Autowired
    private FormSubmitKafkaService formSubmitKafkaService;

    /**
     * Submits a form asynchronously using Kafka.
     *
     * @param formId    The ID of the form to submit.
     * @param submitDto The form submission data.
     * @return A ResponseEntity indicating success or failure.
     */
    @Operation(summary = "Submit a form asynchronously using kafka", description = "Sends form submission data to Kafka for processing.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Form submission request accepted"),
            @ApiResponse(responseCode = "400", description = "Invalid form submission request")
    })
    @PostMapping(path = "formId/{formId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CustomResponse<Boolean>> addSubmit(
            @Parameter(description = "ID of the form being submitted")
            @PathVariable("formId") String formId,
            @RequestBody SubmitDTO submitDto) throws FormException {

        log.info("Received form submission request for formId: {}", formId);

        // Set user email and form ID
        submitDto.setEmail(loggedInUserService.getLoggedInUserEmail());
        submitDto.setFormId(formId);

        boolean submitted = formSubmitKafkaService.addSubmit(submitDto);

        if (submitted) {
            return responseOkDataMessage(true, AppMessage.FORM_SUBMIT.getSubmitSuccessfully());
        } else {
            log.error("Failed to submit form for formId: {}", formId);
            return responseFailDataMessage(false, AppMessage.FORM_SUBMIT.getSubmitFailed());
        }
    }
}
