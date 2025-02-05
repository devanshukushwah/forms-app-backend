package com.formsapp.mapper;

import com.formsapp.dto.SubmissionDTO;
import com.formsapp.entity.projection.Submission;

public class SubmissionMapper {
    public static SubmissionDTO entityToDto(Submission submission) {
        return SubmissionDTO.builder()
                .email(submission.getEmail())
                .createdDate(submission.getCreatedDate())
                .subId(submission.getSubId())
                .build();
    }
}
