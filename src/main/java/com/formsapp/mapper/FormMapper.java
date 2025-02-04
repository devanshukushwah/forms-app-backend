package com.formsapp.mapper;

import com.formsapp.dto.FormDTO;
import com.formsapp.entity.Form;

public class FormMapper {
    public static FormDTO entityToDto(Form form) {
        return FormDTO.builder()
                .formId(form.getFormId())
                .title(form.getTitle())
                .description(form.getDescription())
                .createdDate(form.getCreatedDate())
                .createdBy(form.getCreatedBy())
                .changedDate(form.getChangedDate())
                .changedBy(form.getChangedBy())
                .build();
    }

    public static Form dtoToEntity(FormDTO formDto) {
        return Form.builder()
                .formId(formDto.getFormId())
                .title(formDto.getTitle())
                .description(formDto.getDescription())
                .createdDate(formDto.getCreatedDate())
                .createdBy(formDto.getCreatedBy())
                .changedDate(formDto.getChangedDate())
                .changedBy(formDto.getChangedBy())
                .build();
    }
}
