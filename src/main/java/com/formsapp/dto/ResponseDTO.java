package com.formsapp.dto;


import com.formsapp.entity.FormField;
import com.formsapp.entity.projection.Response;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ResponseDTO {
    /**
     * A Page of form responses.
     * <p>
     * Each response in this page is an instance of {@link Response}, which contains
     * details about a single submission such as the email, submission ID, and creation date.
     * </p>
     */
    private Page<Response> responses;
}
