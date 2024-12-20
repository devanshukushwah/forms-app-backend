package com.formsapp.repository;

import com.formsapp.model.FormSubmit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface FormSubmitRepository extends JpaRepository<FormSubmit, Long> {
    FormSubmit findByFormIdAndEmail(UUID formId, String email);
}
