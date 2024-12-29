package com.formsapp.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;
import java.util.List;

import javax.persistence.*;
import java.util.Date;

/**
 * Entity representing a form with fields for title, description,
 * create time, and update time, along with transient form fields.
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Form {

    @Id // Primary key
    private String formId;

    private String title;

    private String description;

    /**
     * Timestamp when the form was created.
     * Automatically populated when the entity is persisted.
     */
    @Column(updatable = false, nullable = false)
    private Date createdDate;

    /**
     * Timestamp when the form was last updated.
     * Automatically updated whenever the entity is updated.
     */
    private Date changedDate;

    /**
     * List of form fields associated with the form.
     * This field is not persisted in the database.
     */
    @Transient
    private List<FormField> formFields;

    @Transient
    private Long submitsCount;

    /**
     * Callback to set createTime and changedTime before persisting.
     */
    @PrePersist
    protected void onCreate() {
        this.createdDate = new Date();
    }

    /**
     * Callback to update changedTime before updating the entity.
     */
    @PreUpdate
    protected void onUpdate() {
        this.changedDate = new Date();
    }
}

