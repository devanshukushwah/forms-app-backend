package com.formsapp.repository;

import com.formsapp.model.FormSubmit;
import com.formsapp.model.projection.FormResponse;
import com.formsapp.model.projection.SubmitsCount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FormSubmitRepository extends JpaRepository<FormSubmit, Long> {
    FormSubmit findByFormIdAndEmail(String formId, String email);
    FormSubmit findBySubIdAndFormId(Long subId, String formId);
    @Query("SELECT f.email AS email, f.subId AS subId FROM FormSubmit f WHERE f.formId = :formId")
    List<FormResponse> findAllByFormId(String formId);

    @Query("SELECT f.formId AS formId, COUNT(1) as submitsCount FROM FormSubmit f WHERE f.formId IN (:formIds) GROUP BY f.formId")
    List<SubmitsCount> findAllCountsByFormIds(List<String> formIds);

    FormSubmit findBySubId(Long subId);
}
