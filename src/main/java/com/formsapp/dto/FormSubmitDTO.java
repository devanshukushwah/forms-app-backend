package com.formsapp.dto;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.formsapp.entity.FormFieldAnswer;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Entity representing a submission of a form.
 * <p>
 * This class stores the information related to a form submission, including the form ID,
 * the email of the person who submitted the form, and the answers provided during the submission.
 * </p>
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FormSubmitDTO {
    private UUID subId;
    private String formId;
    private String email;
    private Date createdDate;
    private List<FormFieldAnswer> answers;
}
