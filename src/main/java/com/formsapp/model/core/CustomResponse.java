package com.formsapp.model.core;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CustomResponse<T> {
    private boolean success;
    private int statusCode;
    private String message;
    private String errorMessage;
    private T data;
    private Boolean isAppException;
}
