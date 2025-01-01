package com.formsapp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.Arrays;
import java.util.Collections;

/**
 * Configuration class for Cross-Origin Resource Sharing (CORS) settings.
 * <p>
 * This class provides CORS configuration beans for different profiles (local and production)
 * to enable secure and flexible cross-origin requests in the application.
 * </p>
 */
@Configuration
public class CorsConfig {

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
    public CorsFilter corsFilter(CorsConfiguration config) {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);
        return new CorsFilter(source);
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
    @Profile("local")
    @Bean
    public CorsConfiguration corsConfigurationLocal() {
        CorsConfiguration config = new CorsConfiguration();
        config.applyPermitDefaultValues();
        config.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        return config;
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
    @Profile("prod")
    @Bean
    public CorsConfiguration corsConfiguration() {
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        config.setAllowedOrigins(Arrays.asList("http://*makeitcoder.com", "https://*makeitcoder.com"));
        config.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE"));
        config.setAllowedHeaders(Arrays.asList("*"));
        return config;
    }
}
