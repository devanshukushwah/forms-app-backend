package com.formsapp.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Form {
    @Id // Primary key
    @GeneratedValue(strategy = GenerationType.AUTO) // Auto-increment
    private UUID formId;
    private String title;
    private String description;
    @Transient
    private List<FormField> formFields;
}
