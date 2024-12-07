package com.formsapp.controller;

import com.formsapp.common.AppMessage;
import com.formsapp.model.Form;
import com.formsapp.model.core.CustomResponse;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/forms")
public class FormController extends BaseController {

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CustomResponse<Form>> getForms() {
        return responseOk(new Form());
    }

    @RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CustomResponse<String>> addForms() {
        return responseOkMessage(AppMessage.FORM_CREATED_SUCCESSFULLY.getMessage());
    }

    @RequestMapping(method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CustomResponse<String>> updateForms() {
        return responseOkMessage(AppMessage.FORM_UPDATED_SUCCESSFULLY.getMessage());
    }
}
