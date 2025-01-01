package com.formsapp.exception;

import com.formsapp.common.AppErrorMessage;
import com.formsapp.model.core.CustomResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * Global exception handler for managing application-wide exceptions.
 * <p>
 * This class uses {@link ControllerAdvice} to globally handle different types of exceptions thrown
 * during the processing of HTTP requests. It provides customized responses for specific exceptions
 * like {@link FormException}, database errors, and other generic exceptions.
 * </p>
 */
@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Handles {@link FormException} specifically.
     * <p>
     * This method catches {@link FormException} thrown during form processing and returns a custom
     * response with a BAD_REQUEST status.
     * </p>
     *
     * @param ex The {@link FormException} that was thrown.
     * @return A {@link ResponseEntity} containing the error response.
     */
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

    /**
     * Handles all other generic exceptions.
     * <p>
     * This method catches any other uncaught exceptions and returns a custom response with an
     * INTERNAL_SERVER_ERROR status.
     * </p>
     *
     * @param ex The generic {@link Exception} that was thrown.
     * @return A {@link ResponseEntity} containing the error response.
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleGlobalException(Exception ex) {
        log.error("generic error, method handleGlobalException(), message {}", ex.getMessage(), ex);
        CustomResponse<Object> objectCustomResponse = new CustomResponse<>();
        objectCustomResponse.setSuccess(false);
        objectCustomResponse.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
        objectCustomResponse.setErrorMessage(ex.getMessage());
        return new ResponseEntity<>(objectCustomResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * Handles {@link DataAccessException} related to database errors.
     * <p>
     * This method catches database-related exceptions and returns a custom response indicating a
     * database error with an INTERNAL_SERVER_ERROR status.
     * </p>
     *
     * @param ex The {@link DataAccessException} thrown by the database operation.
     * @return A {@link ResponseEntity} containing the error response.
     */
    @ExceptionHandler(DataAccessException.class)
    public ResponseEntity<Object> handleGlobalDatabaseException(Exception ex) {
        log.warn("database error, method handleGlobalDatabaseException(), message {}", ex.getMessage(), ex);
        CustomResponse<Object> objectCustomResponse = new CustomResponse<>();
        objectCustomResponse.setSuccess(false);
        objectCustomResponse.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
        objectCustomResponse.setErrorMessage(AppErrorMessage.DATABASE_ERROR.getMessage());
        return new ResponseEntity<>(objectCustomResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * Handles {@link DataIntegrityViolationException} related to database constraint violations.
     * <p>
     * This method catches database constraint violations (e.g., unique constraint violations) and
     * returns a custom response indicating that the data is not valid with a BAD_REQUEST status.
     * </p>
     *
     * @param ex The {@link DataIntegrityViolationException} thrown due to a database constraint violation.
     * @return A {@link ResponseEntity} containing the error response.
     */
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
