package com.formsapp.model;

import com.formsapp.common.FormFieldType;
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
    private String type = FormFieldType.INPUT.getValue();
    @Column(name = "form_id", nullable = false)
    private UUID formId;
    private String title;
    private String value;
}
