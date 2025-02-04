package com.formsapp.entity.response;

import com.formsapp.entity.projection.FormResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;

/**
 * Response model that encapsulates a Page of form responses.
 * <p>
 * This class is used to return a collection of form responses, typically in response
 * to an API call that queries form submissions.
 * </p>
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SubmitResponse {

    /**
     * A Page of form responses.
     * <p>
     * Each response in this page is an instance of {@link FormResponse}, which contains
     * details about a single submission such as the email, submission ID, and creation date.
     * </p>
     */
    private Page<FormResponse> responses;
}
