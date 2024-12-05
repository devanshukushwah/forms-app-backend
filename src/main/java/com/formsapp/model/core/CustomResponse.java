package com.formsapp.model.core;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Setter
@Getter
public class CustomResponse<T> implements Serializable {
    private static final long serialVersionUID = 1L;
    private T data;
}
