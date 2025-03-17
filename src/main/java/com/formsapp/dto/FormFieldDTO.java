package com.formsapp.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Entity representing a field within a form.
 * <p>
 * This class defines the structure of a form field, which includes its type,
 * whether it's required, and a list of associated attributes. The field is mapped
 * to a database table, and the form's field attributes are stored as child entities.
 * </p>
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FormFieldDTO {
    private Long fieldId;
    private String fieldType;
    private String fieldTitle;
    private Boolean required;
    private String formId;
    private List<FormFieldAttributeDTO> attributes;
}
