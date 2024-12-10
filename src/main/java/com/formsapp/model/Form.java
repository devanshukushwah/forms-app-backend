package com.formsapp.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import springfox.documentation.spring.web.json.Json;

import javax.persistence.*;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

@Entity
@Data
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Form {
    @Id // Primary key
    @GeneratedValue(strategy = GenerationType.AUTO) // Auto-increment
    private UUID id;
    private String title;
    private String description;
    @Transient
    private List<HashMap<String, ?>> formFieldList;
}
