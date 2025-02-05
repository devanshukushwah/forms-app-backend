package com.formsapp.controller;

import com.formsapp.dto.ResponseDTO;
import com.formsapp.dto.core.CustomResponse;
import com.formsapp.service.FormSubmitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * Controller for handling responses to form submissions.
 * <p>
 * This controller exposes an API endpoint to fetch responses for a specific form by its ID.
 * It interacts with the {@link FormSubmitService} to retrieve form submission responses.
 * </p>
 */
@RestController
@RequestMapping("api/v1/responses/{formId}")
public class ResponseController extends BaseController {

    @Autowired
    private FormSubmitService formSubmitService;

    /**
     * Retrieves all form submission responses for a specific form.
     * <p>
     * This method fetches a list of responses associated with the provided form ID.
     * It returns SubmissionDTO list of {@link Submission} objects.
     * </p>
     *
     * @param formId    The ID of the form whose responses are to be fetched.
     * @param page      The page number to retrieve (default is 0).
     * @param size      The number of responses per page (default is 10).
     * @param sortField The field by which to sort the responses (default is "createdDate").
     * @param sortOrder The order in which to sort the responses, either "asc" for ascending or "desc" for descending (default is "asc").
     * @return A {@link ResponseEntity} containing a {@link CustomResponse} with the list of form responses.
     */
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CustomResponse<ResponseDTO>> getResponse(@PathVariable(name = "formId") String formId,
                                                                   @RequestParam(defaultValue = "0") int page,  // Page number (default is 0)
                                                                   @RequestParam(defaultValue = "10") int size, // Page size (default is 10)
                                                                   @RequestParam(defaultValue = "createdDate") String sortField,  // Sorting field (default is "name")
                                                                   @RequestParam(defaultValue = "asc") String sortOrder) {
        ResponseDTO responses = formSubmitService.getResponses(formId, page, size, sortField, sortOrder);
        return responseOk(responses);
    }

}
