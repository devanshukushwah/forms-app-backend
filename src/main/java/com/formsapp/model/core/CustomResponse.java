package com.formsapp.model.core;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

/**
 * A generic response wrapper class used to standardize the structure of API responses.
 * <p>
 * This class can be used for wrapping the result of any API operation. It contains fields
 * for success status, HTTP status code, message, error message, data, and a flag to indicate
 * if the exception was an application-specific exception.
 * </p>
 *
 * @param <T> The type of data that will be included in the response body.
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CustomResponse<T> {

    /**
     * Indicates whether the operation was successful.
     * <p>
     * A value of {@code true} indicates success, and {@code false} indicates failure.
     * </p>
     */
    private boolean success;

    /**
     * The HTTP status code associated with the response.
     * <p>
     * This value indicates the outcome of the request (e.g., 200 for OK, 500 for Internal Server Error).
     * </p>
     */
    private int statusCode;

    /**
     * A message providing additional context or information about the response.
     * <p>
     * This can be a success message, error message, or other relevant information.
     * </p>
     */
    private String message;

    /**
     * A detailed error message providing more information when the request fails.
     * <p>
     * This field is typically populated when an error occurs during the operation.
     * </p>
     */
    private String errorMessage;

    /**
     * The data included in the response.
     * <p>
     * This is a generic field that can hold any type of response data, such as a list of objects,
     * a single entity, or other results of the operation.
     * </p>
     */
    private T data;

    /**
     * Indicates whether the exception was an application-specific exception.
     * <p>
     * This flag is set to {@code true} when the exception is a known application exception,
     * and {@code false} for generic or system exceptions.
     * </p>
     */
    private Boolean isAppException;
}
