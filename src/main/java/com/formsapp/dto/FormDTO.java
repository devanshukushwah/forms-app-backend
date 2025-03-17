package com.formsapp.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FormDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = 987654321098765432L;

    private String formId;
    private String title;
    private String description;
    private Date createdDate;
    private String createdBy;
    private Date changedDate;
    private String changedBy;
    private List<FormFieldDTO> formFields;
    private Long submitsCount;
    private Boolean multipleSubmit;
}
