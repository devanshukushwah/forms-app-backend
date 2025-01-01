package com.formsapp.exception;

/**
 * Custom exception class for handling form-related errors.
 * <p>
 * This class extends {@link Exception} and provides a way to handle exceptions that occur
 * specifically during form-related operations, such as when a form creation or submission fails.
 * </p>
 */
public class FormException extends Exception {

    /**
     * Constructs a new {@link FormException} with the specified detail message.
     * <p>
     * This constructor allows a specific error message to be passed when the exception is thrown.
     * The message will provide details about the error that occurred during form processing.
     * </p>
     *
     * @param message The detail message that explains the reason for the exception.
     */
    public FormException(String message) {
        super(message);
    }
}
