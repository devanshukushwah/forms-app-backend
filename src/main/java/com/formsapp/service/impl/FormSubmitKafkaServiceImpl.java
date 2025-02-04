package com.formsapp.service.impl;

import com.formsapp.entity.FormSubmit;
import com.formsapp.producer.KafkaMessageProducer;
import com.formsapp.service.FormSubmitKafkaService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.concurrent.CompletableFuture;

@Service
@Slf4j
public class FormSubmitKafkaServiceImpl implements FormSubmitKafkaService {

    @Autowired
    private KafkaMessageProducer kafkaMessagePublisher;

    /**
     * Adds a new form submission.
     *
     * @param formSubmit the {@link FormSubmit} entity containing the form submission data
     * @return {@code true} if the submission was successfully added, {@code false} otherwise
     */
    @Override
    public Boolean addSubmit(FormSubmit formSubmit) {
        // add created date.
        formSubmit.setCreatedDate(new Date());

        CompletableFuture<SendResult<String, FormSubmit>> sendMessage = kafkaMessagePublisher.sendFormSubmitMessage(formSubmit);
        sendMessage.whenComplete((result, ex) -> {
            if (ex != null) {
                log.error("Failed to send message: {}", formSubmit.toString(), ex);
            }
        });
        return true;
    }
}
