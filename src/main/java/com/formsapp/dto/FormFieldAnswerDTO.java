package com.formsapp.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * Entity representing an answer to a specific form field.
 * <p>
 * This class stores the answer submitted by the user for a form field,
 * including the value, the associated form field, and the form submission to which the answer belongs.
 * </p>
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FormFieldAnswerDTO {
    private Long ansId;
    private Long fieldId;
    private Long attrId;
    private String value;

    @Override
    public String toString() {
        return "FormFieldAnswer{" +
                "ansId=" + ansId +
                ", fieldId=" + fieldId +
                ", attrId=" + attrId +
                ", value='" + value + '\'' +
                '}';
    }
}
