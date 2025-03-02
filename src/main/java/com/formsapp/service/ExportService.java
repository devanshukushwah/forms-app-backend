package com.formsapp.service;

import java.io.IOException;

public interface ExportService {
    /**
     * Method to download form submission
     * */
    byte[] download(String formId, String format) throws IOException;
}
