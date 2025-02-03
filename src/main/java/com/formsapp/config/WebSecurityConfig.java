package com.formsapp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.Arrays;
import java.util.List;

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
            .cors().and() // Enables CORS support
            .csrf(AbstractHttpConfigurer::disable)
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


    /**
     * Creates a {@link CorsFilter} bean with the provided {@link CorsConfiguration}.
     * <p>
     * This bean will be used to apply the global CORS configuration to all incoming requests.
     * </p>
     *
     * @param config The CORS configuration to be applied to the filter.
     * @return A {@link CorsFilter} bean that applies the provided CORS configuration.
     */
    @Bean
    public CorsConfigurationSource corsConfigurationSource(CorsConfiguration config) {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);
        return source;
    }

    /**
     * Creates a {@link CorsConfiguration} bean for local development profile ("local").
     * <p>
     * This configuration allows all origins and supports the default CORS methods (GET, POST, PUT, DELETE, OPTIONS).
     * It is intended for local development environments.
     * </p>
     *
     * @return A {@link CorsConfiguration} with default permit settings for local profile.
     */
    @Profile(value = {"local", "dev", "test"})
    @Bean
    public CorsConfiguration corsConfigurationLocal() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(List.of("*"));
        configuration.setAllowedMethods(List.of("*"));
        configuration.setAllowedHeaders(List.of("*"));
        return configuration;
    }

    /**
     * Creates a {@link CorsConfiguration} bean for production profile ("prod").
     * <p>
     * This configuration allows specific origins from the "makeitcoder.com" domain, supports a set of methods
     * (GET, POST, PUT, DELETE), and allows all headers to be sent in requests.
     * It is intended for secure production environments.
     * </p>
     *
     * @return A {@link CorsConfiguration} with specific settings for production profile.
     */
    @Profile(value = {"qa", "prod"})
    @Bean
    public CorsConfiguration corsConfiguration() {
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        config.setAllowedOrigins(Arrays.asList("http://*makeitcoder.com", "https://*makeitcoder.com"));
        config.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE"));
        config.setAllowedHeaders(List.of("*"));
        return config;
    }

}
