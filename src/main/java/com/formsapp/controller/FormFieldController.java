package com.formsapp.controller;

import com.formsapp.common.AppMessage;
import com.formsapp.exception.FormException;
import com.formsapp.model.FormField;
import com.formsapp.model.FormFieldAttribute;
import com.formsapp.model.core.CustomResponse;
import com.formsapp.service.FormFieldService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/forms/{formId}/formFields")
public class FormFieldController extends BaseController {

    @Autowired
    private FormFieldService formFieldService;

    @RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CustomResponse<FormField>> addFormsField(@PathVariable String formId, @RequestBody FormField formField) throws FormException {
        FormField res = formFieldService.save(formId, formField);
        if (res != null) {
            return responseOkDataMessage(res, AppMessage.FORM_FIELD.getCreateSuccessfully());
        }
        return responseFailDataMessage(null, AppMessage.FORM_FIELD.getCreateFailed());
    }

    @RequestMapping(method = RequestMethod.PUT, path = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CustomResponse<FormField>> updateFormsField(@PathVariable String formId, @PathVariable Long id, @RequestBody FormField formField) throws FormException {
        FormField res = formFieldService.updateFormField(formId, id, formField);
        if (res != null) {
            return responseOkDataMessage(res, AppMessage.FORM_FIELD.getUpdateSuccessfully());
        }
        return responseFailDataMessage(null, AppMessage.FORM_FIELD.getUpdateFailed());
    }
}

