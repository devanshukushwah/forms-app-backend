package com.formsapp.producer;

import com.formsapp.common.AppConstant;
import com.formsapp.entity.FormSubmit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;

@Component
public class KafkaMessageProducer {

    @Autowired
    KafkaTemplate<String, FormSubmit> kafkaTemplate;

    public CompletableFuture<SendResult<String, FormSubmit>> sendFormSubmitMessage(FormSubmit formSubmit) {
        return kafkaTemplate.send(AppConstant.KAFKA_TOPIC_FORMS_APP, formSubmit);
    }
}
