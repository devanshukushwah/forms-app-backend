package com.formsapp.repository;

import com.formsapp.model.Form;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.UUID;

/**
 * Repository interface for interacting with the `Form` entity.
 * <p>
 * This interface extends {@link JpaRepository}, providing CRUD operations and custom query methods for the `Form` entity.
 * </p>
 */
@Repository
public interface FormRepository extends JpaRepository<Form, UUID> {

    /**
     * Finds a form by its unique form ID.
     *
     * @param formId the ID of the form to find
     * @return the {@link Form} entity associated with the provided form ID
     */
    Form findByFormId(String formId);

    /**
     * Checks if a form exists by its unique form ID.
     *
     * @param formId the ID of the form to check
     * @return {@code true} if the form exists, {@code false} otherwise
     */
    boolean existsByFormId(String formId);

    /**
     * Retrieves a paginated list of all forms.
     *
     * @param pageable the pagination information
     * @return a {@link Page} of {@link Form} entities
     */
    Page<Form> findAll(Pageable pageable);

    /**
     * Counts the number of forms created within a specific date range (from the start to the end of the day).
     *
     * @param startOfDay the start of the day to count records from
     * @param endOfDay the end of the day to count records up to
     * @return the count of {@link Form} records created within the specified date range
     */
    @Query("SELECT COUNT(e) FROM Form e WHERE e.createdDate >= :startOfDay AND e.createdDate < :endOfDay")
    Long countRecordsCreatedToday(@Param("startOfDay") Date startOfDay, @Param("endOfDay") Date endOfDay);

}
