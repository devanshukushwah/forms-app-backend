package com.formsapp.model.response;

import com.formsapp.model.projection.FormResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Response model that encapsulates a list of form responses.
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
     * A list of form responses.
     * <p>
     * Each response in this list is an instance of {@link FormResponse}, which contains
     * details about a single submission such as the email, submission ID, and creation date.
     * </p>
     */
    private List<FormResponse> responses;
}
