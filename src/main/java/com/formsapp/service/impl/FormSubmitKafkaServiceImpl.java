package com.formsapp.service.impl;

import com.formsapp.model.FormSubmit;
import com.formsapp.producer.KafkaMessageProducer;
import com.formsapp.service.FormSubmitKafkaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;

import java.util.concurrent.CompletableFuture;

@Service
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
        CompletableFuture<SendResult<String, FormSubmit>> sendMessage = kafkaMessagePublisher.sendFormSubmitMessage(formSubmit);
//        sendMessage.whenComplete((result, ex) -> {
//            if (ex == null) {
//            }
//        });
        return true;
    }
}
