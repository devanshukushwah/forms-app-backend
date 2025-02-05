package com.formsapp.dto;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.formsapp.entity.FormField;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * Entity representing an attribute of a form field.
 * <p>
 * This class stores additional attributes for form fields, such as their values and order (sqc).
 * Each attribute is associated with a specific form field.
 * </p>
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FormFieldAttributeDTO {
    private Long attrId;
    private String attr;
    private String value;
    private Integer sqc;
}
