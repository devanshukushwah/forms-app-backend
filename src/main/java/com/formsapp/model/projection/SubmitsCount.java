package com.formsapp.model.projection;

/**
 * Projection interface for retrieving the count of submissions for a specific form.
 * <p>
 * This interface is used to fetch the form ID and the corresponding submission count
 * from the data source, without exposing the full form or submission entities.
 * </p>
 */
public interface SubmitsCount {

    /**
     * Gets the unique identifier of the form.
     *
     * @return the ID of the form.
     */
    String getFormId();

    /**
     * Gets the total number of submissions for the specified form.
     *
     * @return the count of submissions for the form.
     */
    Long getSubmitsCount();
}
