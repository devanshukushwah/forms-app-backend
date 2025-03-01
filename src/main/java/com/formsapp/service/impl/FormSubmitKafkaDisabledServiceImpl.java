package com.formsapp.service.impl;

import com.formsapp.dto.SubmitDTO;
import com.formsapp.entity.FormSubmit;
import com.formsapp.producer.KafkaMessageProducer;
import com.formsapp.service.FormSubmitKafkaService;
import com.formsapp.service.FormSubmitService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.concurrent.CompletableFuture;

@Service
@Slf4j
@ConditionalOnProperty(name = "app.feature.kafka-submit", havingValue = "false", matchIfMissing = false)
public class FormSubmitKafkaDisabledServiceImpl implements FormSubmitKafkaService {

    @Autowired
    private FormSubmitService formSubmitService;

    /**
     * Adds a new form submission.
     *
     * @param formSubmit the {@link FormSubmit} entity containing the form submission data
     * @return {@code true} if the submission was successfully added, {@code false} otherwise
     */
    @Override
    public Boolean addSubmit(SubmitDTO submitDto) {
        return formSubmitService.addSubmit(submitDto);
    }
}
