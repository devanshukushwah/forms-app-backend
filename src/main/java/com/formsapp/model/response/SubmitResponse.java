package com.formsapp.model.response;

import com.formsapp.model.FormSubmit;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class SubmitResponse {
    private List<FormSubmit> responses;
    public SubmitResponse(List<FormSubmit> responses) {
        this.responses = responses;
    }
}
