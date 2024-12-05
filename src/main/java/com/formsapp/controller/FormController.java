package com.formsapp.controller;

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

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<CustomResponse<Form>> getForms() {
        return responseOk(new Form());
    }
}
