package com.formsapp.repository;

import com.formsapp.model.FormField;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FormFieldRepository extends JpaRepository<FormField, Long> {
    List<FormField> findByFormId(String formId);
}
