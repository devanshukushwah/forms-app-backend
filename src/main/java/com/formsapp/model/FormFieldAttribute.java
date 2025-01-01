package com.formsapp.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * Entity representing an attribute of a form field.
 * <p>
 * This class stores additional attributes for form fields, such as their values and order (sqc).
 * Each attribute is associated with a specific form field.
 * </p>
 */
@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class FormFieldAttribute {

    /**
     * The unique identifier for the form field attribute.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long attrId;

    /**
     * The name or identifier of the attribute.
     */
    private String attr;

    /**
     * The value associated with the attribute.
     */
    private String value;

    /**
     * The sequence number that indicates the order of the attribute in the form field.
     */
    private Integer sqc;

    /**
     * The form field to which this attribute belongs.
     * This is a many-to-one relationship with the FormField entity.
     */
    @ManyToOne
    @JoinColumn(name = "form_field_id", nullable = false)
    @JsonBackReference
    private FormField formField;
}
