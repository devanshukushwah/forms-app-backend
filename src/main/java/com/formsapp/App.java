package com.formsapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * The entry point for the Spring Boot application.
 * <p>
 * This class contains the main method that initializes and runs the Spring Boot application.
 * It is annotated with {@link SpringBootApplication}, which enables auto-configuration,
 * component scanning, and configuration properties.
 * </p>
 */
@SpringBootApplication
public class App
{
    /**
     * The main method that starts the Spring Boot application.
     *
     * @param args the command-line arguments passed to the application
     */
    public static void main( String[] args )
    {
        SpringApplication.run(App.class, args);
    }
}
