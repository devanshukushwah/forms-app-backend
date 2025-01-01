package com.formsapp.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * Entity representing a form in the system.
 * <p>
 * This entity contains the basic details of a form such as its title, description,
 * creation and modification timestamps, along with the form fields and submission count.
 * </p>
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Form {

    /**
     * The unique identifier for the form.
     * This is the primary key of the form entity.
     */
    @Id // Primary key
    private String formId;

    /**
     * The title of the form.
     */
    private String title;

    /**
     * A short description of the form.
     */
    private String description;

    /**
     * Timestamp indicating when the form was created.
     * This value is automatically populated when the entity is persisted.
     */
    @Column(updatable = false, nullable = false)
    private Date createdDate;

    /**
     * The email or username of the user who created the form.
     */
    @Column(updatable = false, nullable = false)
    private String createdBy;

    /**
     * Timestamp indicating when the form was last updated.
     * This value is automatically updated whenever the entity is updated.
     */
    private Date changedDate;

    /**
     * The email or username of the user who last updated the form.
     */
    private String changedBy;

    /**
     * The list of form fields associated with the form.
     * This field is not persisted in the database and is transient.
     */
    @Transient
    private List<FormField> formFields;

    /**
     * The count of submissions for this form.
     * This field is not persisted in the database and is transient.
     */
    @Transient
    private Long submitsCount;

    /**
     * Callback to set the createdDate before persisting the entity.
     * This method is called before the entity is persisted to set the creation timestamp.
     */
    @PrePersist
    protected void onCreate() {
        this.createdDate = new Date();
    }

    /**
     * Callback to update the changedDate before updating the entity.
     * This method is called before the entity is updated to set the modification timestamp.
     */
    @PreUpdate
    protected void onUpdate() {
        this.changedDate = new Date();
    }
}
