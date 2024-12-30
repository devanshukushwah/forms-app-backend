package com.formsapp.controller;

import com.formsapp.common.AppErrorMessage;
import com.formsapp.exception.FormException;
import com.formsapp.exception.Operation;
import com.formsapp.model.Form;
import com.formsapp.model.FormAndSubmit;
import com.formsapp.model.FormSubmit;
import com.formsapp.model.core.CustomResponse;
import com.formsapp.model.projection.FormResponse;
import com.formsapp.model.response.SubmitResponse;
import com.formsapp.service.FormService;
import com.formsapp.service.FormSubmitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/form-submit")
public class FormAndSubmitController extends BaseController {

    @Autowired
    private FormSubmitService formSubmitService;

    @Autowired
    private FormService formService;

    @GetMapping(path = "{subId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CustomResponse<FormAndSubmit>> getResponse(@PathVariable(name = "subId") Long subId) throws FormException {
        FormSubmit submit = formSubmitService.getSubmit(subId);
        if (submit != null) {
            FormAndSubmit formAndSubmit = new FormAndSubmit();
            formAndSubmit.setSubmit(submit);
            if (submit.getFormId() != null) {
                Form form = formService.getForm(submit.getFormId());
                formAndSubmit.setForm(form);
            }
            return responseOk(formAndSubmit);
        }

        throw new FormException(AppErrorMessage.GET_FORM_SUBMIT_FAILED.getMessage());
    }

}
