package com.formsapp.mapper;

import com.formsapp.dto.FormFieldDTO;
import com.formsapp.entity.FormField;

import java.util.stream.Collectors;

public class FormFieldMapper {
    public static FormFieldDTO entityToDto(FormField formField) {
        FormFieldDTO.FormFieldDTOBuilder builder = FormFieldDTO.builder()
                .fieldId(formField.getFieldId())
                .fieldType(formField.getFieldType())
                .formId(formField.getFormId())
                .required(formField.getRequired());

        if (formField.getAttributes() != null) {
            builder.attributes(formField.getAttributes()
                    .stream()
                    .map(FormFieldAttributeMapper::entityToDto)
                    .collect(Collectors.toList()));
        }

        return builder.build();
    }

    public static FormField dtoToEntity(FormFieldDTO formDto) {
        FormField.FormFieldBuilder builder = FormField.builder()
                .fieldId(formDto.getFieldId())
                .fieldType(formDto.getFieldType())
                .formId(formDto.getFormId())
                .required(formDto.getRequired());

        if (formDto.getAttributes() != null) {
            builder.attributes(formDto.getAttributes()
                    .stream()
                    .map(FormFieldAttributeMapper::dtoToEntity)
                    .collect(Collectors.toList()));
        }

        return builder.build();

    }
}
