package com.formsapp.controller;

import com.formsapp.model.core.CustomResponse;
import com.formsapp.model.projection.FormResponse;
import com.formsapp.model.response.SubmitResponse;
import com.formsapp.service.FormSubmitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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
     * It returns a {@link SubmitResponse} object, which contains a list of {@link FormResponse} objects.
     * </p>
     *
     * @param formId The ID of the form whose responses are to be fetched.
     * @return A {@link ResponseEntity} containing a {@link CustomResponse} with the list of form responses.
     */
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CustomResponse<SubmitResponse>> getResponse(@PathVariable(name = "formId") String formId) {
        List<FormResponse> responses = formSubmitService.getResponses(formId);
        return responseOk(new SubmitResponse(responses));
    }

}
