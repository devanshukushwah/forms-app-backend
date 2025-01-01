package com.formsapp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

/**
 * Configuration class for web security settings.
 * <p>
 * This class configures HTTP security, including CORS support, public endpoints,
 * and OAuth2 authentication with JWT support.
 * </p>
 */
@Configuration
public class WebSecurityConfig {

    /**
     * A list of actuator-related endpoints that should be publicly accessible.
     * <p>
     * These endpoints are typically used for health checks, metrics, and Swagger UI.
     * </p>
     */
    private static String[] ACTUATORS = new String[]{"/v3/api-docs",
            "/swagger-resources/**",
            "/swagger-ui/**",
            "/swagger-ui.html",
            "/webjars/**",
            "/actuator/**"};

    /**
     * Configures HTTP security for the application.
     * <p>
     * This method configures the CORS filter, sets up access controls for various endpoints,
     * and applies OAuth2 resource server settings with JWT authentication.
     * </p>
     *
     * @param http The {@link HttpSecurity} object to configure security settings.
     * @return A configured {@link SecurityFilterChain} for HTTP security.
     * @throws Exception If any configuration exception occurs.
     */
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .cors()  // Enables CORS support
                .and()
                .authorizeHttpRequests()
                .antMatchers(ACTUATORS).permitAll()  // Public access to actuator-related endpoints
                .anyRequest().authenticated()       // All other endpoints require authentication
                .and()
                .oauth2ResourceServer()
                .jwt();  // Configures JWT authentication for OAuth2 resource server
        return http.build();
    }
}
