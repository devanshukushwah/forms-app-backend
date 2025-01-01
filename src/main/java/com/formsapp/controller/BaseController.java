package com.formsapp.controller;

import com.formsapp.model.core.CustomResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

/**
 * Base controller class that provides standardized response methods for handling API responses.
 * <p>
 * This class provides utility methods for returning consistent and structured responses
 * in the form of {@link CustomResponse} objects, including both success and failure responses.
 * </p>
 */
public class BaseController {

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
