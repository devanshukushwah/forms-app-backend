package com.formsapp.mapper;

import com.formsapp.dto.FormFieldDTO;
import com.formsapp.entity.FormField;

public class FormFieldMapper {
    public static FormFieldDTO entityToDto(FormField formField) {
        return FormFieldDTO.builder()
                .fieldId(formField.getFieldId())
                .fieldType(formField.getFieldType())
                .formId(formField.getFormId())
                .attributes(formField.getAttributes())
                .required(formField.getRequired())
                .build();
    }

    public static FormField dtoToEntity(FormFieldDTO formDto) {
        return FormField.builder()
                .fieldId(formDto.getFieldId())
                .fieldType(formDto.getFieldType())
                .formId(formDto.getFormId())
                .attributes(formDto.getAttributes())
                .required(formDto.getRequired())
                .build();
    }
}
