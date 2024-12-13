package com.formsapp.controller;

import com.formsapp.common.AppMessage;
import com.formsapp.exception.Operation;
import com.formsapp.model.Form;
import com.formsapp.model.core.CustomResponse;
import com.formsapp.service.FormService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/api/v1/forms")
public class FormController extends BaseController {

    @Autowired
    private FormService formService;

    @RequestMapping(method = RequestMethod.GET, path = "{uuid}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CustomResponse<Form>> getForms(@PathVariable UUID uuid) {
        Form form = formService.getForm(uuid);
        if(form != null) {
            return responseOk(form);
        }
        return responseFailDataMessage(null, AppMessage.FORM.getFetchFailed());
    }

    @RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CustomResponse<UUID>> addForms(@RequestBody Form form) throws Operation {
        UUID uuid = formService.addForm(form);
        if(form != null) {
            responseOkDataMessage(uuid, AppMessage.FORM.getCreateSuccessfully());
        }
        return responseFailDataMessage(uuid, AppMessage.FORM.getCreateFailed());
    }

    @RequestMapping(method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CustomResponse<String>> updateForms() {
        return responseOkMessage(AppMessage.FORM_UPDATED_SUCCESSFULLY.getMessage());
    }

}
