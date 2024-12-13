package com.formsapp.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity
@Data
public class FormField {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String fieldType;
    @Column(name = "form_id", nullable = false)
    private UUID formId;
    @OneToMany(mappedBy = "formField")
    private List<FormFieldAttribute> formFieldAttributes;
}
