package com.formsapp.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Entity representing a field within a form.
 * <p>
 * This class defines the structure of a form field, which includes its type,
 * whether it's required, and a list of associated attributes. The field is mapped
 * to a database table, and the form's field attributes are stored as child entities.
 * </p>
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FormField {

    /**
     * The unique identifier of the form field.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long fieldId;

    /**
     * The type of the form field (e.g., text, checkbox, dropdown).
     */
    private String fieldType;

    /**
     * Indicates whether the form field is required.
     */
    private Boolean required;

    /**
     * The ID of the form to which this field belongs.
     */
    @Column(name = "form_id", nullable = false)
    private String formId;

    /**
     * A list of attributes associated with this form field.
     * These attributes can include things like field validation, display options, etc.
     */
    @OneToMany(mappedBy = "formField", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<FormFieldAttribute> attributes = new ArrayList<>();
}
