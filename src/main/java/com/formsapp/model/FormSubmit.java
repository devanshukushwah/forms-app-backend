package com.formsapp.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Entity representing a submission of a form.
 * <p>
 * This class stores the information related to a form submission, including the form ID,
 * the email of the person who submitted the form, and the answers provided during the submission.
 * </p>
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FormSubmit {

    /**
     * The unique identifier for the form submission.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long subId;

    /**
     * The ID of the form that is being submitted.
     */
    private String formId;

    /**
     * The email of the person who submitted the form.
     */
    private String email;

    /**
     * Timestamp when the form submission was created.
     * This field is automatically populated when the entity is persisted.
     */
    @Column(updatable = false, nullable = false)
    private Date createdDate;

    /**
     * Callback to set the creation date before persisting the entity.
     */
    @PrePersist
    protected void onCreate() {
        this.createdDate = new Date();
    }

    /**
     * List of answers provided in the form submission.
     * Each answer corresponds to a specific form field.
     * This is a one-to-many relationship with the FormFieldAnswer entity.
     */
    @OneToMany(mappedBy = "formSubmit", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<FormFieldAnswer> answers = new ArrayList<>();


}
