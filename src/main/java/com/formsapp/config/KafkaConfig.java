package com.formsapp.config;

import com.formsapp.common.AppConstant;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaConfig {

    @Bean
    @ConditionalOnProperty(name = "app.feature.kafka-submit", havingValue = "true", matchIfMissing = false)
    public NewTopic topic() {
        return TopicBuilder.name(AppConstant.KAFKA_TOPIC_FORMS_APP).build();
    }
}
