package com.formsapp.controller;

import com.formsapp.model.core.CustomResponse;
import com.formsapp.model.projection.FormResponse;
import com.formsapp.model.response.SubmitResponse;
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
@RequestMapping("api/v1/responses/{formId}")
public class ResponseController extends BaseController {

    @Autowired
    private FormSubmitService formSubmitService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CustomResponse<SubmitResponse>> getResponse(@PathVariable(name = "formId") String formId) {
        List<FormResponse> responses = formSubmitService.getResponses(formId);
        return responseOk(new SubmitResponse(responses));
    }

}
