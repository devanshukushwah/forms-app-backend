package com.formsapp.mapper;

import com.formsapp.dto.SubmitDTO;
import com.formsapp.entity.FormSubmit;

import java.util.stream.Collectors;

public class SubmitMapper {
    public static SubmitDTO entityToDto(FormSubmit formSubmit) {
        SubmitDTO.SubmitDTOBuilder builder = SubmitDTO.builder()
                .formId(formSubmit.getFormId())
                .email(formSubmit.getEmail())
                .subId(formSubmit.getSubId())
                .createdDate(formSubmit.getCreatedDate());

        if (formSubmit.getAnswers() != null) {
            builder.answers(formSubmit.getAnswers()
                    .stream()
                    .map(FormFieldAnswerMapper::entityToDto)
                    .collect(Collectors.toList()));
        }

        return builder.build();
    }

    public static FormSubmit dtoToEntity(SubmitDTO submitDto) {
        FormSubmit.FormSubmitBuilder builder = FormSubmit.builder()
                .formId(submitDto.getFormId())
                .email(submitDto.getEmail())
                .subId(submitDto.getSubId())
                .createdDate(submitDto.getCreatedDate());

        if (submitDto.getAnswers() != null) {
            builder.answers(submitDto.getAnswers()
                    .stream()
                    .map(FormFieldAnswerMapper::dtoToEntity)
                    .collect(Collectors.toList()));
        }

        return builder.build();
    }
}
