package com.formsapp.exception;

public class InvalidFormFieldType extends FormException {
    public InvalidFormFieldType(String type) {
        super("Invalid form field type " + type);
    }
}
