package com.formsapp.repository;

import com.formsapp.model.Form;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface FormRepository extends JpaRepository<Form, UUID> {
    Form findByFormId(String formId);
    boolean existsByFormId(String formId);
    Page<Form> findAll(Pageable pageable);
}
