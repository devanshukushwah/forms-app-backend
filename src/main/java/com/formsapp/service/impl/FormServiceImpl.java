package com.formsapp.service.impl;

import com.formsapp.common.AppConstant;
import com.formsapp.exception.Operation;
import com.formsapp.model.Form;
import com.formsapp.model.projection.SubmitsCount;
import com.formsapp.repository.FormFieldRepository;
import com.formsapp.repository.FormRepository;
import com.formsapp.repository.FormSubmitRepository;
import com.formsapp.service.FormService;
import com.formsapp.util.DateUtils;
import com.formsapp.util.UUIDUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Service
public class FormServiceImpl implements FormService {

    @Autowired
    private FormRepository formRepository;

    @Autowired
    private FormFieldRepository formFieldRepository;

    @Autowired
    private FormSubmitRepository formSubmitRepository;

    /**
     * {@inheritDoc}
     */
    @Override
    public Form getForm(String formId) {
        Form form = formRepository.findByFormId(formId);
        if (form != null) {
            form.setFormFields(formFieldRepository.findByFormId(formId));
        }
        return form;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Page<Form> getAllForm(int page, int size, String sortField, String sortOrder) {
        // Create Sort object based on field and direction
        Sort sort = Sort.by(Sort.Order.by(sortField));
        if ("desc".equalsIgnoreCase(sortOrder)) {
            sort = sort.descending();
        } else {
            sort = sort.ascending();
        }

        // Create Pageable object with page number, page size, and sort order
        Pageable pageable = PageRequest.of(page, size, sort);
        Page<Form> finalResult = formRepository.findAll(pageable);
        if (finalResult != null && !finalResult.getContent().isEmpty()) {
            List<Form> content = finalResult.getContent();
            List<SubmitsCount> allCountsByFormIds = formSubmitRepository.findAllCountsByFormIds(content.stream().map(Form::getFormId).collect(Collectors.toList()));
            Map<String, Long> collect = allCountsByFormIds.stream().collect(Collectors.toMap(SubmitsCount::getFormId, SubmitsCount::getSubmitsCount));
            content.forEach(item -> {
                item.setSubmitsCount(collect.get(item.getFormId()));
            });
        }
        return finalResult;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public synchronized String addForm(Form form) throws Operation {
        String formId = this.generateFormId();
        form.setFormId(formId);

        Form save = formRepository.save(form);
        if (save.getFormId() == null) {
            throw new Operation("failed to add form");
        }

        return save.getFormId();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Form updateForm(String formId, Form form) throws Operation {
        if (!formRepository.existsByFormId(formId)) {
            throw new Operation("form not found");
        }
        form.setFormId(formId);
        return formRepository.save(form);
    }

    /**
     * Method to generate a unique ID for creating a new form.
     * It first generates an ID based on the current pattern and checks if the ID already exists.
     * If it exists, it generates a new UUID.
     *
     * @return generated form ID
     * @throws Operation if generating the form ID fails
     */
    private String generateFormId() throws Operation {
        try {
            String formId = generateMyFormIdPattern();
            while(formRepository.existsByFormId(formId)) {
                formId = UUIDUtils.generateUUID();
            }
            return formId;
        } catch (Exception ex) {
            log.error("failed to generate formId {} ", ex.getMessage(), ex);
        }
        throw new Operation("failed to add form");
    }

    /**
     * Method to get the count of form records created today.
     * It calculates the start and end of the current day and queries the repository for the count of forms created within that time range.
     *
     * @return count of records created today
     */
    private Long getTodayRecordCount() {
        LocalDate today = LocalDate.now();
        LocalDateTime startOfDay = today.atStartOfDay();
        LocalDateTime endOfDay = today.plusDays(1).atStartOfDay();

        // Convert to Date if needed (for JPA or Hibernate)
        Date start = Date.from(startOfDay.atZone(ZoneId.systemDefault()).toInstant());
        Date end = Date.from(endOfDay.atZone(ZoneId.systemDefault()).toInstant());

        return formRepository.countRecordsCreatedToday(start, end);
    }

    /**
     * Method to generate the form ID pattern based on the current date and the number of forms created today.
     * The form ID follows the pattern of yyyyMMdd-XXX, where XXX is the number of forms created today, padded to three digits.
     *
     * @return generated form ID pattern
     */
    private String generateMyFormIdPattern() {
        Long todayFormCreatedCount = getTodayRecordCount();
        String dateString = DateUtils.getDateStringInPattern(AppConstant.DATE_yyyyMMdd);

        String end = null;
        if (todayFormCreatedCount <= 999) {
            end = String.format("%03d", todayFormCreatedCount);
        } else {
            end = String.valueOf(todayFormCreatedCount);
        }

        return dateString + AppConstant.HYPHEN_F + end;
    }

}
