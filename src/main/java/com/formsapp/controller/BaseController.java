package com.formsapp.controller;

import com.formsapp.common.AppErrorMessage;
import com.formsapp.dto.core.CustomResponse;
import com.formsapp.exception.FormException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * Base controller class that provides standardized response methods for handling API responses.
 * <p>
 * This class provides utility methods for returning consistent and structured responses
 * in the form of {@link CustomResponse} objects, including both success and failure responses.
 * </p>
 */
@ControllerAdvice
@Slf4j
public class BaseController {


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

    /**
     * Returns a successful response with data.
     * <p>
     * This method returns an HTTP 200 OK response with the provided data wrapped inside a {@link CustomResponse}.
     * </p>
     *
     * @param data The data to be returned in the response body.
     * @param <T> The type of the data.
     * @return A {@link ResponseEntity} containing a {@link CustomResponse} with the provided data.
     */
    public <T> ResponseEntity<CustomResponse<T>> responseOk(T data) {
        CustomResponse<T> customResponse = new CustomResponse<>();
        customResponse.setSuccess(Boolean.TRUE);
        customResponse.setData(data);
        customResponse.setStatusCode(HttpStatus.OK.value());
        return ResponseEntity.ok(customResponse);
    }

    /**
     * Returns a successful response with a message.
     * <p>
     * This method returns an HTTP 200 OK response with a success message wrapped inside a {@link CustomResponse}.
     * </p>
     *
     * @param message The success message to be returned in the response body.
     * @return A {@link ResponseEntity} containing a {@link CustomResponse} with the success message.
     */
    public ResponseEntity<CustomResponse<String>> responseOkMessage(String message) {
        CustomResponse<String> customResponse = new CustomResponse<>();
        customResponse.setSuccess(Boolean.TRUE);
        customResponse.setStatusCode(HttpStatus.OK.value());
        customResponse.setMessage(message);
        return ResponseEntity.ok(customResponse);
    }

    /**
     * Returns a failed response with a message.
     * <p>
     * This method returns an HTTP 500 Internal Server Error response with a failure message wrapped inside a {@link CustomResponse}.
     * </p>
     *
     * @param message The failure message to be returned in the response body.
     * @return A {@link ResponseEntity} containing a {@link CustomResponse} with the failure message.
     */
    public ResponseEntity<CustomResponse<String>> responseFailMessage(String message) {
        CustomResponse<String> customResponse = new CustomResponse<>();
        customResponse.setSuccess(Boolean.FALSE);
        customResponse.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
        customResponse.setMessage(message);
        return ResponseEntity.ok(customResponse);
    }

    /**
     * Returns a failed response with both data and a message.
     * <p>
     * This method returns an HTTP 500 Internal Server Error response with both data and a failure message wrapped inside a {@link CustomResponse}.
     * </p>
     *
     * @param data The data to be included in the response body.
     * @param message The failure message to be returned in the response body.
     * @param <T> The type of the data.
     * @return A {@link ResponseEntity} containing a {@link CustomResponse} with the data and failure message.
     */
    public <T> ResponseEntity<CustomResponse<T>> responseFailDataMessage(T data, String message) {
        CustomResponse<T> customResponse = new CustomResponse<>();
        customResponse.setSuccess(Boolean.FALSE);
        customResponse.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
        customResponse.setMessage(message);
        customResponse.setData(data);
        return ResponseEntity.ok(customResponse);
    }

    /**
     * Returns a successful response with both data and a message.
     * <p>
     * This method returns an HTTP 200 OK response with both data and a success message wrapped inside a {@link CustomResponse}.
     * </p>
     *
     * @param data The data to be included in the response body.
     * @param message The success message to be returned in the response body.
     * @param <T> The type of the data.
     * @return A {@link ResponseEntity} containing a {@link CustomResponse} with the data and success message.
     */
    public <T> ResponseEntity<CustomResponse<T>> responseOkDataMessage(T data, String message) {
        CustomResponse<T> customResponse = new CustomResponse<>();
        customResponse.setSuccess(Boolean.TRUE);
        customResponse.setStatusCode(HttpStatus.OK.value());
        customResponse.setMessage(message);
        customResponse.setData(data);
        return ResponseEntity.ok(customResponse);
    }
}
