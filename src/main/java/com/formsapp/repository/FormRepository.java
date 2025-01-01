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

@Repository
public interface FormRepository extends JpaRepository<Form, UUID> {
    Form findByFormId(String formId);
    boolean existsByFormId(String formId);
    Page<Form> findAll(Pageable pageable);

    @Query("SELECT COUNT(e) FROM Form e WHERE e.createdDate >= :startOfDay AND e.createdDate < :endOfDay")
    Long countRecordsCreatedToday(@Param("startOfDay") Date startOfDay, @Param("endOfDay") Date endOfDay);

}
