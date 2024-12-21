package com.formsapp.controller;

import com.formsapp.common.AppMessage;
import com.formsapp.model.FormSubmit;
import com.formsapp.model.core.CustomResponse;
import com.formsapp.service.FormSubmitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/forms/{formId}/submits")
public class FormSubmitController extends BaseController {

    @Autowired
    private FormSubmitService formSubmitService;

    @RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CustomResponse<Boolean>> addSubmit(@PathVariable("formId") String formId, @RequestBody FormSubmit formSubmit) {
        Boolean res = formSubmitService.addSubmit(formSubmit);
        if(res) {
            return responseOkDataMessage(true, AppMessage.FORM_SUBMIT.getSubmitSuccessfully());
        } else {
            return responseFailDataMessage(false, AppMessage.FORM_SUBMIT.getSubmitFailed());
        }
    }

    @RequestMapping(method = RequestMethod.GET, path = "{email}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CustomResponse<FormSubmit>> getSubmit(@PathVariable("formId") String formId, @PathVariable String email) {
        FormSubmit formSubmit = formSubmitService.getSubmit(formId, email);
        if(formSubmit != null) {
            return responseOk(formSubmit);
        } else {
            return responseFailDataMessage(null, AppMessage.FORM_SUBMIT.getFetchFailed());
        }
    }
}
