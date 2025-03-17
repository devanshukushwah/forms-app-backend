package com.formsapp.service.impl;

import com.formsapp.dto.FormFieldDTO;
import com.formsapp.entity.FormField;
import com.formsapp.exception.FormException;
import com.formsapp.mapper.FormFieldMapper;
import com.formsapp.repository.FormFieldRepository;
import com.formsapp.service.FormFieldService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;

@Service
public class FormFieldServiceImpl implements FormFieldService {

    @Autowired
    private FormFieldRepository formFieldRepository;

    /**
     * {@inheritDoc}
     */
    @Override
    public FormFieldDTO save(String formId, FormFieldDTO formFieldDto) throws FormException {
        FormField formField = FormFieldMapper.dtoToEntity(formFieldDto);

        formField.setFormId(formId);

        // Ensure the bidirectional relationship is maintained
        if (formField.getAttributes() != null) {
            formField.getAttributes().forEach(attribute -> attribute.setFormField(formField));
        }

        return FormFieldMapper.entityToDto(formFieldRepository.save(formField));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @CacheEvict(value = "forms", key = "#formId")
    public FormFieldDTO updateFormField(String formId, Long formFieldId, FormFieldDTO formFieldDto) throws FormException {
        formFieldDto.setFieldId(formFieldId);
        return save(formId, formFieldDto);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @CacheEvict(value = "forms", key = "#formId")
    public Boolean deleteFormField(String formId, Long formFieldId) {
        formFieldRepository.deleteById(formFieldId);
        return !formFieldRepository.existsById(formFieldId);
    }
}
