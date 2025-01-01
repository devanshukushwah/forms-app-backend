package com.formsapp.util;

import com.formsapp.common.AppConstant;

import java.util.Random;

public class UUIDUtils {

    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private static final int RANDOM_LENGTH = 4;
    private static final Random RANDOM = new Random();

    /**
     * Generates a unique identifier based on the current date and a random alphanumeric string.
     * <p>
     * The generated UUID follows the pattern: {@code YYYYMMDD-RRRR}, where:
     * - {@code YYYYMMDD} is the current date in the format {@code yyyyMMdd}.
     * - {@code RRRR} is a random alphanumeric string consisting of 4 characters.
     * </p>
     *
     * @return a unique identifier as a string
     */
    public static String generateUUID() {
        // Get the current date
        String date = DateUtils.getDateStringInPattern(AppConstant.DATE_yyyyMMdd);

        // Generate random characters
        StringBuilder randomPart = new StringBuilder(RANDOM_LENGTH);
        for (int i = 0; i < RANDOM_LENGTH; i++) {
            int index = RANDOM.nextInt(CHARACTERS.length());
            randomPart.append(CHARACTERS.charAt(index));
        }

        // Combine date and random part
        return date + "-" + randomPart;
    }
}
