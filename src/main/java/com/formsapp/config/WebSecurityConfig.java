package com.formsapp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class WebSecurityConfig {

    private static String[] ACTUATORS = new String[]{"/v3/api-docs",
            "/swagger-resources/**",
            "/swagger-ui/**",
            "/swagger-ui.html",
            "/webjars/**",
            "/actuator/**"};

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
         http
                 .cors()
                 .and()
                 .authorizeHttpRequests()
                 .antMatchers(ACTUATORS).permitAll()  // Public endpoints
                 .anyRequest().authenticated()             // Secure other endpoints
                 .and()
                 .oauth2ResourceServer()
                 .jwt();
        return http.build();
    }
}
