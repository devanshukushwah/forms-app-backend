package com.formsapp.controller;

import com.formsapp.model.core.CustomResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class BaseController {

    public <T> ResponseEntity<CustomResponse<T>> responseOk(T data) {
        CustomResponse<T> customResponse = new CustomResponse<>();
//        customResponse.setData(data);
        customResponse.setData(data);
        return ResponseEntity.ok(customResponse);
    }
}
