package com.formsapp.controller;

import com.formsapp.model.core.CustomResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class BaseController {

    public <T> ResponseEntity<CustomResponse<T>> responseOk(T data) {
        CustomResponse<T> customResponse = new CustomResponse<>();
        customResponse.setSuccess(Boolean.TRUE);
        customResponse.setData(data);
        customResponse.setStatusCode(HttpStatus.OK.value());
        return ResponseEntity.ok(customResponse);
    }

    public ResponseEntity<CustomResponse<String>> responseOkMessage(String message) {
        CustomResponse<String> customResponse = new CustomResponse<>();
        customResponse.setSuccess(Boolean.TRUE);
        customResponse.setStatusCode(HttpStatus.OK.value());
        customResponse.setMessage(message);
        return ResponseEntity.ok(customResponse);
    }
}
