package com.formsapp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.web.SecurityFilterChain;

/**
 * Configuration class for web security settings.
 * <p>
 * This class configures HTTP security, including CORS support, public endpoints,
 * and OAuth2 authentication with JWT support.
 * </p>
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    /**
     * A list of actuator-related endpoints that should be publicly accessible.
     * <p>
     * These endpoints are typically used for health checks, metrics, and Swagger UI.
     * </p>
     */
    private static String[] ACTUATORS = new String[]{"/v3/api-docs", "/v3/api-docs/*",
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
    @Profile({"dev", "qa", "prod"})
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .cors(AbstractHttpConfigurer::disable) // Enables CORS support
            .authorizeHttpRequests(authorize -> authorize
                    .requestMatchers(ACTUATORS).permitAll() // Public access to actuator-related endpoints
                    .anyRequest().authenticated()          // All other endpoints require authentication
            )
            .oauth2ResourceServer(oauth2 -> oauth2
                    .jwt(jwt -> jwt
                            .jwtAuthenticationConverter(new JwtAuthenticationConverter()) // Custom JWT conversion
                    )
            );
        return http.build();
    }

    @Bean
    @Profile({"local", "test"})
    public SecurityFilterChain filterChainLocal(HttpSecurity http) throws Exception {
        return http.cors(AbstractHttpConfigurer::disable).authorizeHttpRequests(auth -> auth.anyRequest().permitAll()).csrf(AbstractHttpConfigurer::disable).build();
    }
}
