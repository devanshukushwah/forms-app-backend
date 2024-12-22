package com.formsapp.config;

import com.formsapp.common.AppErrorMessage;
import com.formsapp.exception.FormException;
import com.formsapp.model.core.CustomResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(FormException.class)
    public ResponseEntity<CustomResponse<?>> handleFormException(FormException ex) {
        log.info("form app warnings, method handleFormException(), message {}", ex.getMessage(), ex);
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
        log.error("generic error, method handleGlobalException(), message {}", ex.getMessage(), ex);
        CustomResponse<Object> objectCustomResponse = new CustomResponse<>();
        objectCustomResponse.setSuccess(false);
        objectCustomResponse.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
        objectCustomResponse.setErrorMessage(ex.getMessage());
        return new ResponseEntity<>(objectCustomResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(DataAccessException.class)
    public ResponseEntity<Object> handleGlobalDatabaseException(Exception ex) {
        log.warn("database error, method handleGlobalDatabaseException(), message {}", ex.getMessage(), ex);
        CustomResponse<Object> objectCustomResponse = new CustomResponse<>();
        objectCustomResponse.setSuccess(false);
        objectCustomResponse.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
        objectCustomResponse.setErrorMessage(AppErrorMessage.DATABASE_ERROR.getMessage());
        return new ResponseEntity<>(objectCustomResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<Object> handleGlobalDatabaseConstraintException(Exception ex) {
        log.info("database constraint error, method handleGlobalDatabaseConstraintException(), message {}", ex.getMessage(), ex);
        CustomResponse<Object> objectCustomResponse = new CustomResponse<>();
        objectCustomResponse.setSuccess(false);
        objectCustomResponse.setIsAppException(true);
        objectCustomResponse.setStatusCode(HttpStatus.BAD_REQUEST.value());
        objectCustomResponse.setErrorMessage(AppErrorMessage.DATA_IS_NOT_VALID.getMessage());
        return new ResponseEntity<>(objectCustomResponse, HttpStatus.BAD_REQUEST);
    }

}
