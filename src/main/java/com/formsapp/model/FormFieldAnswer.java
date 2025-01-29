package com.formsapp.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * Entity representing an answer to a specific form field.
 * <p>
 * This class stores the answer submitted by the user for a form field,
 * including the value, the associated form field, and the form submission to which the answer belongs.
 * </p>
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FormFieldAnswer {

    /**
     * The unique identifier of the form field answer.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long ansId;

    /**
     * The ID of the form field to which this answer corresponds.
     */
    private Long fieldId;

    /**
     * The ID of the field attribute associated with this answer.
     */
    private Long attrId;

    /**
     * The value of the answer provided by the user.
     */
    private String value;

    /**
     * The form submission to which this field answer belongs.
     * This is a many-to-one relationship with the FormSubmit entity.
     */
    @ManyToOne
    @JoinColumn(name = "form_submit_id", nullable = false)
    @JsonBackReference
    private FormSubmit formSubmit;

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
