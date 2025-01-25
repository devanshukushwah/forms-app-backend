package com.formsapp.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

class DateUtilsTest {

    @Test
    void getDateStringInPatternCheckNotEmptyTest() {
        // Given
        String dateFormat = "yyyyMMdd";

        // When
        String date = DateUtils.getDateStringInPattern(dateFormat);

        // Then
        assertFalse(date.isEmpty());
    }

    @Test
    void getDateStringInPatternCheckLengthTest() {
        // Given
        String dateFormat = "yyyyMMdd";

        // When
        String date = DateUtils.getDateStringInPattern(dateFormat);

        // Then
        assertEquals(date.length(),dateFormat.length());
    }
}