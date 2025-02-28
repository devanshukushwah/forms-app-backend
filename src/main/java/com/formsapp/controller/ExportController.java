package com.formsapp.controller;

import com.formsapp.common.AppConstant;
import com.formsapp.common.AppErrorMessage;
import com.formsapp.exception.FormException;
import com.formsapp.service.ExportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("api/v1/exports")
public class ExportController extends BaseController {

    @Autowired
    ExportService exportService;

    @GetMapping(path = "forms/{formId}")
    public ResponseEntity<byte[]> export(@PathVariable(name = "formId") String formId, @RequestParam(value = "format", defaultValue = "csv") String format) throws FormException, IOException {
        byte[] download = exportService.download(formId, format);
        if (download != null) {
            String headerValue = String.format("attachment; filename=%s.%s", formId, format);
            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, headerValue)
                    .contentType(MediaType.parseMediaType(AppConstant.FILE_TEXT_CSV))
                    .body(download);
        }

        throw new FormException(AppErrorMessage.FAILED_TO_EXPORT.getMessage());
    }
}
