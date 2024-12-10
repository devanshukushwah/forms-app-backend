package com.formsapp.config;

import com.formsapp.exception.FormException;
import com.formsapp.model.core.CustomResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(FormException.class)
    public ResponseEntity<CustomResponse> handleFormException(FormException ex) {
        CustomResponse<Object> objectCustomResponse = new CustomResponse<>();
        objectCustomResponse.setSuccess(false);
        objectCustomResponse.setStatusCode(HttpStatus.BAD_REQUEST.value());
        objectCustomResponse.setIsAppException(true);
        objectCustomResponse.setErrorMessage(ex.getMessage());
        return new ResponseEntity<>(objectCustomResponse, HttpStatus.BAD_REQUEST);
    }

    // Handle generic exception
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleGlobalException(Exception ex) {
        CustomResponse<Object> objectCustomResponse = new CustomResponse<>();
        objectCustomResponse.setSuccess(false);
        objectCustomResponse.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
        objectCustomResponse.setErrorMessage(ex.getMessage());
        return new ResponseEntity<>(objectCustomResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
