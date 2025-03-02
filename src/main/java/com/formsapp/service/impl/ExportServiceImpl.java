package com.formsapp.service.impl;

import com.formsapp.common.AppConstant;
import com.formsapp.entity.FormField;
import com.formsapp.entity.FormFieldAnswer;
import com.formsapp.entity.FormSubmit;
import com.formsapp.repository.FormFieldRepository;
import com.formsapp.repository.FormSubmitRepository;
import com.formsapp.service.ExportService;
import com.formsapp.service.FormFieldService;
import com.opencsv.CSVWriter;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class ExportServiceImpl implements ExportService {

    @Autowired
    FormFieldRepository formFieldRepository;

    @Autowired
    FormSubmitRepository formSubmitRepository;

    /**
     * Method to return csv bytes
     *
     * @param fields  headers
     * @param submits submission
     */
    private byte[] downloadCSV(List<FormField> fields, List<FormSubmit> submits) throws IOException {
        String[] header = new String[fields.size() + 1];
        header[0] = "Email";
        String[][] rows = new String[submits.size()][fields.size() + 1];

        int headerIdx = 1;
        for (FormField formField : fields) {
            // set header
            header[headerIdx] = formField.getFieldTitle();

            // set row / response / answer
            int rowIdx = 0;
            for (FormSubmit submit: submits) {
                rows[rowIdx][0] = submit.getEmail();
                List<FormFieldAnswer> answers = Optional.ofNullable(submit.getAnswers()).orElse(Collections.emptyList());
                if (!answers.isEmpty() && formField.getFieldId() != null) {
                    String ans = answers.stream()
                            .filter(item -> item != null && formField.getFieldId().equals(item.getFieldId()))
                            .map(FormFieldAnswer::getValue)
                            .filter(Objects::nonNull) // Ensure value is not null
                            .findFirst()
                            .orElse(StringUtils.EMPTY);

                    rows[rowIdx++][headerIdx] = ans;
                }

            }
            headerIdx++;
        }

        // Create a ByteArrayOutputStream to write CSV to
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

        // Use OpenCSV's CSVWriter to write the data into the output stream
        try (CSVWriter csvWriter = new CSVWriter(new OutputStreamWriter(byteArrayOutputStream))) {
            // Write the header
            csvWriter.writeNext(header);

            // Write the data rows
            for (String[] row : rows) {
                csvWriter.writeNext(row);
            }
        }

        return byteArrayOutputStream.toByteArray();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public byte[] download(String formId, String format) throws IOException {

        List<FormField> fields = formFieldRepository.findByFormId(formId);
        List<FormSubmit> submits = formSubmitRepository.findAllByFormId(formId);

        if (AppConstant.CSV.equalsIgnoreCase(format)) {
            return downloadCSV(fields, submits);
        }

        return new byte[0];
    }
}
