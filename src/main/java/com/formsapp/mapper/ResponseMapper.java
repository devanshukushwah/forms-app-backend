package com.formsapp.mapper;

import com.formsapp.dto.FormSubmitDTO;
import com.formsapp.dto.ResponseDTO;
import com.formsapp.entity.FormSubmit;
import com.formsapp.entity.projection.Response;

public class ResponseMapper {
    public static ResponseDTO entityToDto(Response response) {
        return ResponseDTO.builder()
                .email(response.getEmail())
                .createdDate(response.getCreatedDate())
                .subId(response.getSubId())
                .build();
    }
}
