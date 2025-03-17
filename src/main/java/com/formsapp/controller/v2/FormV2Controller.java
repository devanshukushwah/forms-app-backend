package com.formsapp.controller.v2;

import com.formsapp.annotation.FormEditPermissionAnnotation;
import com.formsapp.common.AppMessage;
import com.formsapp.controller.BaseController;
import com.formsapp.dto.FormDTO;
import com.formsapp.dto.core.CustomResponse;
import com.formsapp.exception.FormException;
import com.formsapp.service.FormService;
import com.formsapp.service.LoggedInUserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Controller for handling form-related requests.
 * <p>
 * This controller exposes API endpoints for creating, retrieving, and updating forms.
 * It interacts with the {@link FormService} to perform CRUD operations on forms.
 * </p>
 */
@Slf4j
@Tag(name = "Form Controller", description = "APIs for managing forms") // Swagger tag for categorizing APIs
@RestController
@RequestMapping("/api/v2/forms")
public class FormV2Controller extends BaseController {

    @Autowired
    private FormService formService;

    /**
     * Retrieves a specific form by its ID using cache.
     * This is used to fetch form for submit page.
     *
     * @param formId The ID of the form to be retrieved.
     * @return A ResponseEntity containing the form details if found, or an error message if not found.
     */
    @Operation(summary = "Retrieve a form by ID", description = "Fetches a specific form using its ID using cache") // Swagger summary
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Form retrieved successfully"),
            @ApiResponse(responseCode = "500", description = "Form not found")
    })
    @GetMapping(value = "{formId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CustomResponse<FormDTO>> getFormsCached(
            @Parameter(description = "ID of the form to be retrieved") // Swagger parameter
            @PathVariable String formId
            ) {
        FormDTO formDto = formService.getFormCached(formId);
        if (formDto != null) {
            return responseOk(formDto); // Returns success response if form is found
        }
        return responseFailDataMessage(null, AppMessage.FORM.getNotFound()); // Returns failure response if form is not found
    }

}
