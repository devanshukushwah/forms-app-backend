package com.formsapp.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * Controller for handling test requests.
 * <p>
 * This controller exposes a simple API endpoint for testing purposes.
 * It returns the current time when accessed.
 * </p>
 */
@RestController
@RequestMapping("/test")
public class TestController {

    /**
     * Test endpoint that returns the current date and time.
     * <p>
     * This method is useful for checking the availability of the API and ensures the server is running.
     * It returns a string containing the current date and time when the request is made.
     * </p>
     *
     * @return A string message indicating the time at which the test was performed.
     */
    @GetMapping
    public String test() {
        return "Tested at time " + new Date().toString();
    }
}
