package com.formsapp.util;

import lombok.extern.slf4j.Slf4j;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Utility class for handling date-related operations.
 */
@Slf4j
public class DateUtils {

    /**
     * Generates a string representation of the current date in the specified pattern.
     *
     * @param pattern the desired date pattern (e.g., "yyyy-MM-dd", "yyyyMMdd", "dd/MM/yyyy").
     *                Refer to {@link java.text.SimpleDateFormat} for pattern syntax.
     * @return a string representing the current date in the specified format.
     * @throws IllegalArgumentException if the pattern is invalid.
     */
    public static String getDateStringInPattern(String pattern) {
        return new SimpleDateFormat(pattern).format(new Date());
    }
}

