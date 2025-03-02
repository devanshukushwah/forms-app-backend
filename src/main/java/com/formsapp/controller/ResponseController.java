package com.formsapp.controller;

import com.formsapp.dto.ResponseDTO;
import com.formsapp.dto.core.CustomResponse;
import com.formsapp.service.FormSubmitService;
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
 * Controller for handling responses to form submissions.
 * <p>
 * This controller provides an API endpoint to fetch responses for a specific form.
 * It interacts with the {@link FormSubmitService} to retrieve form submission responses.
 * </p>
 */
@Slf4j // Enables logging
@Tag(name = "Form Responses", description = "APIs to retrieve form submission responses") // API categorization for Swagger
@RestController
@RequestMapping("api/v1/responses/{formId}")
public class ResponseController extends BaseController {

    @Autowired
    private FormSubmitService formSubmitService;

    /**
     * Retrieves paginated form submission responses for a specific form.
     *
     * @param formId    The ID of the form whose responses are to be fetched.
     * @param page      The page number to retrieve (default: 0).
     * @param size      The number of responses per page (default: 10).
     * @param sortField The field by which to sort the responses (default: "createdDate").
     * @param sortOrder The sorting order, either "asc" or "desc" (default: "desc").
     * @return A {@link ResponseEntity} containing a {@link CustomResponse} with the list of form responses.
     */
    @Operation(summary = "Get form responses", description = "Fetches all responses for a given form ID with pagination and sorting.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Responses retrieved successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid request parameters")
    })
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CustomResponse<ResponseDTO>> getResponse(
            @Parameter(description = "ID of the form whose responses are being retrieved")
            @PathVariable(name = "formId") String formId,

            @Parameter(description = "Page number for pagination (default: 0)")
            @RequestParam(defaultValue = "0") int page,

            @Parameter(description = "Number of responses per page (default: 10)")
            @RequestParam(defaultValue = "10") int size,

            @Parameter(description = "Field to sort responses by (default: createdDate)")
            @RequestParam(defaultValue = "createdDate") String sortField,

            @Parameter(description = "Sorting order: 'asc' (ascending) or 'desc' (descending) (default: desc)")
            @RequestParam(defaultValue = "desc") String sortOrder) {

        log.info("Fetching form responses for formId: {} with page: {}, size: {}, sortField: {}, sortOrder: {}",
                formId, page, size, sortField, sortOrder);

        ResponseDTO responses = formSubmitService.getResponses(formId, page, size, sortField, sortOrder);

        if (responses == null) {
            log.warn("No responses found for formId: {}", formId);
            return responseFailDataMessage(null, "No responses found for the given form.");
        }

        return responseOk(responses);
    }
}
