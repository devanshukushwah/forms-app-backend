package com.formsapp.controller;

import com.formsapp.common.AppMessage;
import com.formsapp.exception.FormException;
import com.formsapp.model.Form;
import com.formsapp.model.core.CustomResponse;
import com.formsapp.service.FormService;
import com.formsapp.util.LoggedInUserUtils;
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
@RestController
@RequestMapping("/api/v1/forms")
public class FormController extends BaseController {

    @Autowired
    private FormService formService;

    /**
     * Retrieves a specific form by its ID.
     * <p>
     * This method fetches a form by its ID and returns it in a successful response if found.
     * If the form is not found, an error response is returned.
     * </p>
     *
     * @param formId The ID of the form to be retrieved.
     * @return A {@link ResponseEntity} containing a {@link CustomResponse} with the form details if found,
     *         or an error message if the form is not found.
     */
    @RequestMapping(method = RequestMethod.GET, path = "{formId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CustomResponse<Form>> getForms(@PathVariable String formId) {
        Form form = formService.getForm(formId);
        if(form != null) {
            return responseOk(form);
        }
        return responseFailDataMessage(null, AppMessage.FORM.getNotFound());
    }

    /**
     * Retrieves a paginated list of all forms.
     * <p>
     * This method fetches all forms in a paginated format with sorting options.
     * The list of forms is returned in a successful response.
     * If no forms are found, an error response is returned.
     * </p>
     *
     * @param page The page number to retrieve (default is 0).
     * @param size The page size (default is 10).
     * @param sortField The field to sort by (default is "createdDate").
     * @param sortOrder The order to sort by ("asc" or "desc", default is "desc").
     * @return A {@link ResponseEntity} containing a {@link CustomResponse} with the paginated list of forms if found,
     *         or an error message if no forms are found.
     */
    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CustomResponse<Page<Form>>> getAllForms(
            @RequestParam(defaultValue = "0") int page,  // Page number (default is 0)
            @RequestParam(defaultValue = "10") int size, // Page size (default is 10)
            @RequestParam(defaultValue = "createdDate") String sortField,  // Sorting field (default is "name")
            @RequestParam(defaultValue = "desc") String sortOrder
    ) {
        Page<Form> forms = formService.getAllForm(page, size, sortField, sortOrder);
        if(forms != null) {
            return responseOk(forms);
        }
        return responseFailDataMessage(null, AppMessage.FORM.getNotFound());
    }

    /**
     * Adds a new form to the system.
     * <p>
     * This method accepts a {@link Form} object in the request body and saves it to the system.
     * The newly created form's ID is returned if the creation is successful.
     * If the form cannot be created, a {@link FormException} is thrown.
     * </p>
     *
     * @param form The form object to be created.
     * @return A {@link ResponseEntity} containing a {@link CustomResponse} with the created form's ID and a success message.
     * @throws FormException If the form cannot be created.
     */
    @RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CustomResponse<String>> addForms(@RequestBody Form form) throws FormException {
        form.setCreatedBy(LoggedInUserUtils.getLoggedInUserEmail());
        String formId = formService.addForm(form);
        if(formId != null) {
            return responseOkDataMessage(formId, AppMessage.FORM.getCreateSuccessfully());
        }
        throw new FormException(AppMessage.FORM.getCreateFailed());
    }

    /**
     * Updates an existing form.
     * <p>
     * This method accepts a {@link Form} object and its ID to update the existing form in the system.
     * The updated form details are returned if the update is successful.
     * If the update fails, a {@link FormException} is thrown.
     * </p>
     *
     * @param formId The ID of the form to be updated.
     * @param form The form object with updated details.
     * @return A {@link ResponseEntity} containing a {@link CustomResponse} with the updated form and a success message.
     * @throws FormException If the form cannot be updated.
     */
    @RequestMapping(method = RequestMethod.PUT, path = "{formId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CustomResponse<Form>> updateForms(@PathVariable("formId") String formId, @RequestBody Form form) throws FormException {
        form.setChangedBy(LoggedInUserUtils.getLoggedInUserEmail());
        Form res = formService.updateForm(formId, form);
        if (res != null) {
            return responseOkDataMessage(res, AppMessage.FORM.getUpdateSuccessfully());
        }
        throw new FormException(AppMessage.FORM.getUpdateFailed());
    }
}
