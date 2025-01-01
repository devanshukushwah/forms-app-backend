package com.formsapp.repository;

import com.formsapp.model.FormField;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository interface for interacting with the `FormField` entity.
 * <p>
 * This interface extends {@link JpaRepository}, providing CRUD operations and custom query methods for the `FormField` entity.
 * </p>
 */
@Repository
public interface FormFieldRepository extends JpaRepository<FormField, Long> {

    /**
     * Finds a list of form fields associated with a specific form ID.
     *
     * @param formId the ID of the form to find fields for
     * @return a list of {@link FormField} entities associated with the provided form ID
     */
    List<FormField> findByFormId(String formId);
}
