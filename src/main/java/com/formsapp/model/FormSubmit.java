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
public class FormSubmit {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long subId;
    private UUID formId;
    private String email;

    @OneToMany(mappedBy = "formSubmit", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<FormFieldAnswer> answers = new ArrayList<>();
}
