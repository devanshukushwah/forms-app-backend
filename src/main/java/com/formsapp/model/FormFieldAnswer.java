package com.formsapp.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FormFieldAnswer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long ansId;
    private Long fieldId;
    private Long attrId;
    private String value;

    @ManyToOne
    @JoinColumn(name = "form_submit_id", nullable = false)
    @JsonBackReference
    private FormSubmit formSubmit;
}
