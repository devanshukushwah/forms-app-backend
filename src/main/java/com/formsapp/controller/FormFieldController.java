package com.formsapp.controller;

import com.formsapp.annotation.FormEditPermissionAnnotation;
import com.formsapp.common.AppMessage;
import com.formsapp.dto.FormFieldDTO;
import com.formsapp.dto.core.CustomResponse;
import com.formsapp.exception.FormException;
import com.formsapp.service.FormFieldService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Controller for handling form field-related requests.
 * <p>
 * This controller exposes API endpoints for creating and updating form fields within a specific form.
 * It interacts with the {@link FormFieldService} to perform CRUD operations on form fields.
 * </p>
 */
@Tag(name = "Form Field Controller", description = "APIs for managing form fields within forms") // Swagger tag for API categorization
@RestController
@RequestMapping("api/v1/forms/{formId}/formFields")
public class FormFieldController extends BaseController {

    @Autowired
    private FormFieldService formFieldService;

    /**
     * Adds a new form field to a form.
     *
     * @param formId The ID of the form to which the field will be added.
     * @param formFieldDto The form field object to be created.
     * @return A ResponseEntity containing the created form field details and a success message.
     * @throws FormException If the form field cannot be created.
     */
    @Operation(summary = "Add a new form field", description = "Creates a new field in a specific form.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Form field created successfully"),
            @ApiResponse(responseCode = "500", description = "Form field creation failed")
    })
    @FormEditPermissionAnnotation // Custom annotation for edit permissions
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CustomResponse<FormFieldDTO>> addFormsField(
            @Parameter(description = "ID of the form to which the field will be added")
            @PathVariable String formId,
            @RequestBody FormFieldDTO formFieldDto) throws FormException {

        FormFieldDTO res = formFieldService.save(formId, formFieldDto);
        if (res != null) {
            return responseOkDataMessage(res, AppMessage.FORM_FIELD.getCreateSuccessfully()); // Success response
        }
        return responseFailDataMessage(null, AppMessage.FORM_FIELD.getCreateFailed()); // Failure response
    }

    /**
     * Updates an existing form field within a specific form.
     *
     * @param formId The ID of the form to which the field belongs.
     * @param id The ID of the form field to be updated.
     * @param formFieldDto The form field object with updated details.
     * @return A ResponseEntity containing the updated form field details and a success message.
     * @throws FormException If the form field cannot be updated.
     */
    @Operation(summary = "Update a form field", description = "Updates an existing field in a specified form.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Form field updated successfully"),
            @ApiResponse(responseCode = "500", description = "Form field update failed")
    })
    @FormEditPermissionAnnotation
    @PutMapping(value = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CustomResponse<FormFieldDTO>> updateFormsField(
            @Parameter(description = "ID of the form to which the field belongs")
            @PathVariable String formId,
            @Parameter(description = "ID of the form field to be updated")
            @PathVariable Long id,
            @RequestBody FormFieldDTO formFieldDto) throws FormException {

        FormFieldDTO res = formFieldService.updateFormField(formId, id, formFieldDto);
        if (res != null) {
            return responseOkDataMessage(res, AppMessage.FORM_FIELD.getUpdateSuccessfully()); // Success response
        }
        return responseFailDataMessage(null, AppMessage.FORM_FIELD.getUpdateFailed()); // Failure response
    }
}
