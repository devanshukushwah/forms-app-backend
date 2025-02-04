package com.formsapp.mapper;

import com.formsapp.dto.FormSubmitDTO;
import com.formsapp.entity.FormSubmit;

public class FormSubmitMapper {
    public static FormSubmitDTO entityToDto(FormSubmit formSubmit) {
        return FormSubmitDTO.builder()
                .formId(formSubmit.getFormId())
                .email(formSubmit.getEmail())
                .subId(formSubmit.getSubId())
                .answers(formSubmit.getAnswers())
                .createdDate(formSubmit.getCreatedDate())
                .build();
    }

    public static FormSubmit dtoToEntity(FormSubmitDTO formSubmitDto) {
        return FormSubmit.builder()
                .formId(formSubmitDto.getFormId())
                .email(formSubmitDto.getEmail())
                .subId(formSubmitDto.getSubId())
                .answers(formSubmitDto.getAnswers())
                .createdDate(formSubmitDto.getCreatedDate())
                .build();
    }
}
