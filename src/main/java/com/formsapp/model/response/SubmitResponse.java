package com.formsapp.model.response;

import com.formsapp.model.projection.FormResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SubmitResponse {
    private List<FormResponse> responses;
}
