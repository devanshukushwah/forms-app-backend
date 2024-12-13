package com.formsapp.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
public class FormFieldAttribute {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "form_field_id", nullable = false)
    private FormField formField;
    private String attr;
    private String value;
    private Integer order;

    public FormFieldAttribute(String attr, String value) {
        this.attr = attr;
        this.value = value;
    }
}

