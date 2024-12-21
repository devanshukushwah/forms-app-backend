package com.formsapp.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FormField {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long fieldId;
    private String fieldType;
    private Boolean required;

    @Column(name = "form_id", nullable = false)
    private String formId;

    @OneToMany(mappedBy = "formField", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<FormFieldAttribute> attributes = new ArrayList<>();
}
