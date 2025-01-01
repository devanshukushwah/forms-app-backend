package com.formsapp.repository;

import com.formsapp.model.FormSubmit;
import com.formsapp.model.projection.FormResponse;
import com.formsapp.model.projection.SubmitsCount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository interface for interacting with the `FormSubmit` entity.
 * <p>
 * This interface extends {@link JpaRepository}, providing CRUD operations and custom query methods for the `FormSubmit` entity.
 * </p>
 */
@Repository
public interface FormSubmitRepository extends JpaRepository<FormSubmit, Long> {

    /**
     * Finds a form submission by its associated form ID and email address.
     *
     * @param formId the ID of the form to find the submission for
     * @param email the email address of the form submitter
     * @return the {@link FormSubmit} entity associated with the given formId and email, or {@code null} if not found
     */
    FormSubmit findByFormIdAndEmail(String formId, String email);

    /**
     * Finds a form submission by its submission ID and form ID.
     *
     * @param subId the submission ID
     * @param formId the ID of the form that the submission belongs to
     * @return the {@link FormSubmit} entity associated with the given subId and formId, or {@code null} if not found
     */
    FormSubmit findBySubIdAndFormId(Long subId, String formId);

    /**
     * Finds all form submissions for a specific form ID, including submission details such as email and submission time.
     *
     * @param formId the ID of the form to find submissions for
     * @return a list of {@link FormResponse} projections containing the email, submission ID, and creation date of each submission
     */
    @Query("SELECT f.email AS email, f.subId AS subId, f.createdDate as createdDate FROM FormSubmit f WHERE f.formId = :formId")
    List<FormResponse> findAllByFormId(String formId);

    /**
     * Finds the count of form submissions for a list of form IDs.
     *
     * @param formIds a list of form IDs to count submissions for
     * @return a list of {@link SubmitsCount} projections, each containing the form ID and the corresponding submission count
     */
    @Query("SELECT f.formId AS formId, COUNT(1) as submitsCount FROM FormSubmit f WHERE f.formId IN (:formIds) GROUP BY f.formId")
    List<SubmitsCount> findAllCountsByFormIds(List<String> formIds);

    /**
     * Finds a form submission by its submission ID.
     *
     * @param subId the submission ID to find
     * @return the {@link FormSubmit} entity associated with the given submission ID, or {@code null} if not found
     */
    FormSubmit findBySubId(Long subId);
}
