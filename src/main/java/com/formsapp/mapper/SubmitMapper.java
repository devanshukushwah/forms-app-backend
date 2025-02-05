package com.formsapp.mapper;

import com.formsapp.dto.SubmitDTO;
import com.formsapp.entity.Submit;

import java.util.stream.Collectors;

public class SubmitMapper {
    public static SubmitDTO entityToDto(Submit submit) {
        SubmitDTO.SubmitDTOBuilder builder = SubmitDTO.builder()
                .formId(submit.getFormId())
                .email(submit.getEmail())
                .subId(submit.getSubId())
                .createdDate(submit.getCreatedDate());

        if (submit.getAnswers() != null) {
            builder.answers(submit.getAnswers()
                    .stream()
                    .map(FormFieldAnswerMapper::entityToDto)
                    .collect(Collectors.toList()));
        }

        return builder.build();
    }

    public static Submit dtoToEntity(SubmitDTO submitDto) {
        Submit.SubmitBuilder builder = Submit.builder()
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
