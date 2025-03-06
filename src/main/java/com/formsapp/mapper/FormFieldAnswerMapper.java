package com.formsapp.mapper;

import com.formsapp.dto.FormFieldAnswerDTO;
import com.formsapp.entity.FormFieldAnswer;

public class FormFieldAnswerMapper {
    public static FormFieldAnswerDTO entityToDto(FormFieldAnswer prop) {
        return FormFieldAnswerDTO.builder()
                .ansId(prop.getAnsId())
                .value(prop.getValue())
                .fieldId(prop.getFieldId())
                .attrId(prop.getAttrId())
                .build();
    }

    public static FormFieldAnswer dtoToEntity(FormFieldAnswerDTO prop) {
        return FormFieldAnswer.builder()
                .ansId(prop.getAnsId())
                .value(prop.getValue())
                .fieldId(prop.getFieldId())
                .attrId(prop.getAttrId())
                .build();
    }
}
