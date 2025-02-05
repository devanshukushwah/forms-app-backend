package com.formsapp.mapper;

import com.formsapp.dto.FormFieldAttributeDTO;
import com.formsapp.entity.FormFieldAttribute;

public class FormFieldAttributeMapper {
    public static FormFieldAttributeDTO entityToDto(FormFieldAttribute prop) {
        return FormFieldAttributeDTO.builder()
                .attrId(prop.getAttrId())
                .attr(prop.getAttr())
                .value(prop.getValue())
                .sqc(prop.getSqc())
                .build();
    }

    public static FormFieldAttribute dtoToEntity(FormFieldAttributeDTO prop) {
        return FormFieldAttribute.builder()
                .attrId(prop.getAttrId())
                .attr(prop.getAttr())
                .value(prop.getValue())
                .sqc(prop.getSqc())
                .build();
    }
}
