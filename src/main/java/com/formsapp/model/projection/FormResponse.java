package com.formsapp.model.projection;

import java.util.Date;

/**
 * Projection interface for the form submission response.
 * <p>
 * This interface defines the fields that represent the response for a form submission.
 * It is used to fetch specific properties of a form submission from the database or other
 * data sources, without exposing the entire entity.
 * </p>
 */
public interface FormResponse {

    /**
     * Gets the email associated with the form submission.
     *
     * @return the email of the user who submitted the form.
     */
    String getEmail();

    /**
     * Gets the unique identifier of the form submission.
     *
     * @return the ID of the form submission.
     */
    Long getSubId();

    /**
     * Gets the date and time when the form submission was created.
     *
     * @return the creation date of the form submission.
     */
    Date getCreatedDate();
}
