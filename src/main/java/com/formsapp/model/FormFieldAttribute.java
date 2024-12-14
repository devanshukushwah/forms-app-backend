package com.formsapp.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class FormFieldAttribute {
    /* primary key */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    /* object attributes */
    private String attr;
    private String value;
    private Integer sqc;

    @ManyToOne
    @JoinColumn(name = "form_field_id", nullable = false)
    @JsonBackReference
    private FormField formField;
}