package com.formsapp.repository;

import com.formsapp.model.Form;
import com.formsapp.model.FormFieldInput;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface FormFieldInputRepository extends JpaRepository<FormFieldInput, Long> {
    List<FormFieldInput> findByFormId(UUID uuid);
}
