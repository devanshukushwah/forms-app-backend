package com.formsapp.consumer;

import com.formsapp.common.AppConstant;
import com.formsapp.model.FormSubmit;
import com.formsapp.service.FormSubmitService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class KafkaMessageConsumer {

    @Autowired
    private FormSubmitService formSubmitService;

    @KafkaListener(topics = AppConstant.KAFKA_TOPIC_FORMS_APP, groupId = "${spring.kafka.consumer.group-id}")
    public void consumer(FormSubmit formSubmit) {
        try {
            formSubmitService.addSubmit(formSubmit);
        } catch (Exception ex) {
            log.error("Exception during form submit consume, message={}, data={}", ex.getMessage(), formSubmit.toString());
        }
    }
}
