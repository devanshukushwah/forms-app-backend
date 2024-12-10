package com.formsapp.exception;

/**
 * Exception to throw when api is failing during set of instruction.
 * */
public class Operation extends FormException {
    public Operation(String message) {
        super(message);
    }

    public Operation() {
        super("not able to process your request");
    }
}
