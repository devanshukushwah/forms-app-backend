package com.formsapp.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * Entity representing a form with fields for title, description,
 * create time, and update time, along with transient form fields.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FormAndSubmit {
    private Form form;
    private FormSubmit submit;
}

