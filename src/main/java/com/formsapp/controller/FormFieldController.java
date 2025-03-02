package com.formsapp.controller;

import com.formsapp.annotation.FormEditPermissionAnnotation;
import com.formsapp.common.AppMessage;
import com.formsapp.dto.FormFieldDTO;
import com.formsapp.exception.FormException;
import com.formsapp.dto.core.CustomResponse;
import com.formsapp.service.FormFieldService;
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
@RestController
@RequestMapping("api/v1/forms/{formId}/formFields")
public class FormFieldController extends BaseController {

    @Autowired
    private FormFieldService formFieldService;

    /**
     * Adds a new form field to a form.
     * <p>
     * This method accepts a {@link FormField} object in the request body and saves it to the specified form.
     * If the form field is successfully created, the details of the newly created form field are returned.
     * If the creation fails, an error message is returned.
     * </p>
     *
     * @param formId The ID of the form to which the field will be added.
     * @param formField The form field object to be created.
     * @return A {@link ResponseEntity} containing a {@link CustomResponse} with the created form field and a success message.
     * @throws FormException If the form field cannot be created.
     */
    @FormEditPermissionAnnotation
    @RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CustomResponse<FormFieldDTO>> addFormsField(@PathVariable String formId, @RequestBody FormFieldDTO formFieldDto) throws FormException {
        FormFieldDTO res = formFieldService.save(formId, formFieldDto);
        if (res != null) {
            return responseOkDataMessage(res, AppMessage.FORM_FIELD.getCreateSuccessfully());
        }
        return responseFailDataMessage(null, AppMessage.FORM_FIELD.getCreateFailed());
    }

    /**
     * Updates an existing form field within a specific form.
     * <p>
     * This method accepts a {@link FormField} object and its ID to update the corresponding form field for a specified form.
     * If the form field is successfully updated, the updated form field details are returned.
     * If the update fails, an error message is returned.
     * </p>
     *
     * @param formId The ID of the form to which the field belongs.
     * @param id The ID of the form field to be updated.
     * @param formField The form field object with updated details.
     * @return A {@link ResponseEntity} containing a {@link CustomResponse} with the updated form field and a success message.
     * @throws FormException If the form field cannot be updated.
     */
    @FormEditPermissionAnnotation
    @RequestMapping(method = RequestMethod.PUT, path = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CustomResponse<FormFieldDTO>> updateFormsField(@PathVariable String formId, @PathVariable Long id, @RequestBody FormFieldDTO formFieldDto) throws FormException {
        FormFieldDTO res = formFieldService.updateFormField(formId, id, formFieldDto);
        if (res != null) {
            return responseOkDataMessage(res, AppMessage.FORM_FIELD.getUpdateSuccessfully());
        }
        return responseFailDataMessage(null, AppMessage.FORM_FIELD.getUpdateFailed());
    }
}
