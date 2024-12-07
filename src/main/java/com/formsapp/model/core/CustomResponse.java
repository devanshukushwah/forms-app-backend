package com.formsapp.model.core;

import lombok.Data;

@Data
public class CustomResponse<T> {
    private boolean success;
    private T data;
    private int statusCode;
    private String message;
}
