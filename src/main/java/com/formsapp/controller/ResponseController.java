package com.formsapp.controller;

import com.formsapp.model.FormSubmit;
import com.formsapp.model.core.CustomResponse;
import com.formsapp.model.response.SubmitResponse;
import com.formsapp.repository.FormSubmitRepository;
import com.formsapp.service.FormSubmitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/responses/{formId}")
public class ResponseController extends BaseController {

    @Autowired
    private FormSubmitService formSubmitService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CustomResponse<SubmitResponse>> getResponse(@PathVariable(name = "formId") String formId) {
        List<FormSubmit> responses = formSubmitService.getResponses(formId);
        return responseOk(new SubmitResponse(responses));
    }

}
