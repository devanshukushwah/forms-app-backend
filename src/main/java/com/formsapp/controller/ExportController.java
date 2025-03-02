package com.formsapp.controller;

import com.formsapp.common.AppConstant;
import com.formsapp.common.AppErrorMessage;
import com.formsapp.exception.FormException;
import com.formsapp.service.ExportService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

/**
 * Controller for exporting form submission data.
 * <p>
 * This controller handles exporting form submission data in different formats (CSV, JSON, etc.).
 * It interacts with the {@link ExportService} to generate and return the export file.
 * </p>
 */
@Slf4j // Enables logging
@Tag(name = "Export", description = "APIs for exporting form submission data") // Swagger API category
@RestController
@RequestMapping("api/v1/exports")
public class ExportController extends BaseController {

    @Autowired
    private ExportService exportService;

    /**
     * Exports form submission data in the requested format.
     *
     * @param formId The unique ID of the form to be exported.
     * @param format The export format (default: "csv").
     * @return A downloadable file containing form submission data.
     * @throws FormException If export fails due to missing data or errors.
     * @throws IOException   If file processing fails.
     */
    @Operation(summary = "Export form submissions", description = "Exports form submission data in CSV or JSON format.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully exported form data"),
            @ApiResponse(responseCode = "500", description = "Failed to export form data")
    })
    @GetMapping(path = "forms/{formId}")
    public ResponseEntity<byte[]> export(
            @PathVariable(name = "formId") String formId,
            @RequestParam(value = "format", defaultValue = "csv") String format) throws FormException, IOException {

        // Validate format
        if (!format.equalsIgnoreCase(AppConstant.CSV)) {
            log.warn("Invalid export format requested: {}", format);
            throw new com.formsapp.exception.Operation("Unsupported export format: " + format);
        }

        // Generate export file
        byte[] fileData = exportService.download(formId, format);
        if (fileData == null) {
            log.error("Failed to generate export file for formId: {}", formId);
            throw new com.formsapp.exception.Operation(AppErrorMessage.FAILED_TO_EXPORT.getMessage());
        }

        // Set appropriate content type
        String headerValue = String.format("attachment; filename=%s.%s", formId, format);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, headerValue)
                .contentType(MediaType.parseMediaType(AppConstant.FILE_TEXT_CSV))
                .body(fileData);
    }
}
