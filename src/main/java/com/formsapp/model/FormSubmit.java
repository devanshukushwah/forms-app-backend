package com.formsapp.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FormSubmit {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long subId;
    private String formId;
    private String email;

    /**
     * Timestamp when the form was created.
     * Automatically populated when the entity is persisted.
     */
    @Column(updatable = false, nullable = false)
    private Date createdDate;

    /**
     * Callback to set createTime and changedTime before persisting.
     */
    @PrePersist
    protected void onCreate() {
        this.createdDate = new Date();
    }

    @OneToMany(mappedBy = "formSubmit", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<FormFieldAnswer> answers = new ArrayList<>();
}
