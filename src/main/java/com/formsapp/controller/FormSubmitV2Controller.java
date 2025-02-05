package com.formsapp.controller;

import com.formsapp.common.AppMessage;
import com.formsapp.dto.SubmitDTO;
import com.formsapp.exception.FormException;
import com.formsapp.dto.core.CustomResponse;
import com.formsapp.service.FormSubmitKafkaService;
import com.formsapp.service.LoggedInUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/v2/submits")
public class FormSubmitV2Controller extends BaseController {

    @Autowired
    private LoggedInUserService loggedInUserService;

    @Autowired
    private FormSubmitKafkaService formSubmitKafkaService;


    @RequestMapping(method = RequestMethod.POST, path = "formId/{formId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CustomResponse<Boolean>> addSubmit(@PathVariable("formId") String formId, @RequestBody SubmitDTO submitDto) throws FormException {
        submitDto.setEmail(loggedInUserService.getLoggedInUserEmail());
        submitDto.setFormId(formId);
        if (formSubmitKafkaService.addSubmit(submitDto)) {
            return responseOkDataMessage(true, AppMessage.FORM_SUBMIT.getSubmitSuccessfully());
        } else {
            return responseFailDataMessage(false, AppMessage.FORM_SUBMIT.getSubmitFailed());
        }
    }
}
