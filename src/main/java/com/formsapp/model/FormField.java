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
    private Long id;
    private String fieldType;
    @Column(name = "form_id", nullable = false)
    private UUID formId;

    @OneToMany(mappedBy = "formField", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<FormFieldAttribute> attributes = new ArrayList<>();
}
