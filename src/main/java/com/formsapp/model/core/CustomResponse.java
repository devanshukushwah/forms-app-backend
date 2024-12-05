package com.formsapp.model.core;

import lombok.Data;

@Data
public class CustomResponse<T> {
    private T data;
}
