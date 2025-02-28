package com.formsapp.common;

/**
 * A utility class to store application-wide constants.
 * <p>
 * This class provides commonly used constants for formatting and other purposes.
 * Constants are declared as {@code public static final} to ensure they are globally accessible,
 * unmodifiable, and memory efficient.
 * </p>
 */
public class AppConstant {

    /**
     * Date format pattern representing year, month, and day in the format {@code yyyyMMdd}.
     * <p>
     * Example: {@code 20250101} represents January 1, 2025.
     * </p>
     */
    public static final String DATE_yyyyMMdd = "yyyyMMdd";

    /**
     * A constant string with a hyphen followed by an uppercase 'F' ({@code "-F"}).
     * <p>
     * This can be used as a suffix or delimiter in formatted strings.
     * </p>
     */
    public static final String HYPHEN_F = "-F";

    public static final String KAFKA_TOPIC_FORMS_APP = "TOPIC-FORMS-APP";

    public static final String FILE_TEXT_CSV = "text/csv";

    public static final String CSV = "csv";

}
