package com.formsapp.config;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI springShopOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Forms App API")
                        .description("FormsApp is a web-based application that allows users to create, submit, and track forms. It provides functionalities such as form creation, field definition, form submission, and form response tracking, all using RESTful APIs. The application supports user authentication, and forms can be dynamically generated and managed.")
                        .version("v2.1.0")
                );
    }
}
