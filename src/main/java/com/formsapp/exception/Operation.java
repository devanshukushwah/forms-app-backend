package com.formsapp.exception;

/**
 * Custom exception class to indicate a failure during the execution of a set of instructions.
 * <p>
 * This exception is a subclass of {@link FormException} and is used when an operation cannot be
 * processed or completed successfully. It provides a default message ("not able to process your request")
 * or allows custom messages to be passed when throwing the exception.
 * </p>
 */
public class Operation extends FormException {

    /**
     * Constructor to create a new {@link Operation} exception with a custom message.
     *
     * @param message The custom message to be associated with the exception.
     */
    public Operation(String message) {
        super(message);
    }

    /**
     * Default constructor for {@link Operation} that uses a standard error message.
     * <p>
     * The default message is "not able to process your request", indicating a generic failure
     * during operation processing.
     * </p>
     */
    public Operation() {
        super("not able to process your request");
    }
}
