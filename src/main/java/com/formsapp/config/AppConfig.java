package com.formsapp.config;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configuration class for application-wide bean definitions.
 * <p>
 * This class defines and configures beans that will be managed by the Spring container.
 * </p>
 */
@Configuration
public class AppConfig {

    /**
     * Configures and provides a customized {@link ObjectMapper} bean.
     * <p>
     * The {@link ObjectMapper} is configured to ignore unknown properties during deserialization
     * to ensure flexibility when mapping JSON data to Java objects.
     * </p>
     *
     * @return A customized {@link ObjectMapper} instance.
     */
    @Bean
    public ObjectMapper objectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        return objectMapper;
    }
}
