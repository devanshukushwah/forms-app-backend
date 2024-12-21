package com.formsapp.repository;

import com.formsapp.model.FormSubmit;
import com.formsapp.model.projection.FormResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FormSubmitRepository extends JpaRepository<FormSubmit, Long> {
    FormSubmit findByFormIdAndEmail(String formId, String email);
    @Query("SELECT f.email AS email, f.subId AS subId FROM FormSubmit f WHERE f.formId = :formId")
    List<FormResponse> findAllByFormId(String formId);
}
