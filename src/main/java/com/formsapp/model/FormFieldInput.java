package com.formsapp.model;

import lombok.Data;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Data
public class FormFieldInput {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Transient
    private String type = "input";
    @Column(name = "form_id", nullable = false)
    private UUID formId;
    private String title;
    private String value;
}
