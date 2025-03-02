package com.formsapp.controller;

import com.formsapp.annotation.FormEditPermissionAnnotation;
import com.formsapp.common.AppMessage;
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
@RequestMapping("/api/v1/forms")
public class FormController extends BaseController {

    @Autowired
    private FormService formService;

    @Autowired
    private LoggedInUserService loggedInUserService;

    /**
     * Retrieves a specific form by its ID.
     *
     * @param formId The ID of the form to be retrieved.
     * @return A ResponseEntity containing the form details if found, or an error message if not found.
     */
    @Operation(summary = "Retrieve a form by ID", description = "Fetches a specific form using its ID.") // Swagger summary
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Form retrieved successfully"),
            @ApiResponse(responseCode = "500", description = "Form not found")
    })
    @GetMapping(value = "{formId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CustomResponse<FormDTO>> getForms(
            @Parameter(description = "ID of the form to be retrieved") // Swagger parameter
            @PathVariable String formId) {
        FormDTO formDto = formService.getForm(formId);
        if (formDto != null) {
            return responseOk(formDto); // Returns success response if form is found
        }
        return responseFailDataMessage(null, AppMessage.FORM.getNotFound()); // Returns failure response if form is not found
    }

    /**
     * Retrieves a paginated list of all forms.
     *
     * @param page The page number to retrieve (default is 0).
     * @param size The page size (default is 10).
     * @param sortField The field to sort by (default is "createdDate").
     * @param sortOrder The order to sort by ("asc" or "desc", default is "desc").
     * @return A ResponseEntity containing a paginated list of forms.
     */
    @Operation(summary = "Retrieve all forms with pagination", description = "Fetches a paginated list of forms.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Forms retrieved successfully"),
            @ApiResponse(responseCode = "500", description = "No forms found")
    })
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CustomResponse<Page<FormDTO>>> getAllForms(
            @Parameter(description = "Page number") @RequestParam(defaultValue = "0") int page,
            @Parameter(description = "Page size") @RequestParam(defaultValue = "10") int size,
            @Parameter(description = "Field to sort by") @RequestParam(defaultValue = "createdDate") String sortField,
            @Parameter(description = "Sort order (asc/desc)") @RequestParam(defaultValue = "desc") String sortOrder
    ) throws FormException {
        String loggedInUser = loggedInUserService.getLoggedInUserEmail(); // Get logged-in user
        Page<FormDTO> forms = formService.getAllFormByCreatedBy(loggedInUser, page, size, sortField, sortOrder);
        if (forms != null) {
            return responseOk(forms); // Returns success response if forms are found
        }
        return responseFailDataMessage(null, AppMessage.FORM.getNotFound()); // Returns failure response if no forms are found
    }

    /**
     * Adds a new form to the system.
     *
     * @param formDto The form object to be created.
     * @return A ResponseEntity containing the created form's ID and a success message.
     * @throws FormException If the form cannot be created.
     */
    @Operation(summary = "Create a new form", description = "Adds a new form to the system.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Form created successfully"),
            @ApiResponse(responseCode = "500", description = "Form creation failed")
    })
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CustomResponse<String>> addForms(@RequestBody FormDTO formDto) throws FormException {
        formDto.setCreatedBy(loggedInUserService.getLoggedInUserEmail()); // Set the creator of the form
        String formId = formService.addForm(formDto);
        if (formId != null) {
            return responseOkDataMessage(formId, AppMessage.FORM.getCreateSuccessfully()); // Returns success response
        }
        throw new FormException(AppMessage.FORM.getCreateFailed()); // Throws exception if form creation fails
    }

    /**
     * Updates an existing form.
     *
     * @param formId The ID of the form to be updated.
     * @param formDto The form object with updated details.
     * @return A ResponseEntity containing the updated form and a success message.
     * @throws FormException If the form cannot be updated.
     */
    @Operation(summary = "Update an existing form", description = "Updates a form using its ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Form updated successfully"),
            @ApiResponse(responseCode = "500", description = "Form update failed")
    })
    @FormEditPermissionAnnotation // Custom annotation for edit permissions
    @PutMapping(value = "{formId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CustomResponse<FormDTO>> updateForms(
            @Parameter(description = "ID of the form to be updated") @PathVariable("formId") String formId,
            @RequestBody FormDTO formDto) throws FormException {
        formDto.setChangedBy(loggedInUserService.getLoggedInUserEmail()); // Set the modifier of the form
        FormDTO res = formService.updateForm(formId, formDto);
        if (res != null) {
            return responseOkDataMessage(res, AppMessage.FORM.getUpdateSuccessfully()); // Returns success response
        }
        throw new FormException(AppMessage.FORM.getUpdateFailed()); // Throws exception if form update fails
    }
}
