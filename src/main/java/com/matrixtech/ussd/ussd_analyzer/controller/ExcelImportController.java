package com.matrixtech.ussd.ussd_analyzer.controller;

import com.matrixtech.ussd.ussd_analyzer.service.ExcelImportService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/excel")  // Endpoint for Excel import operations
@RequiredArgsConstructor  // Generates a constructor with required arguments (ExcelImportService)
@Slf4j  // Enables logging for the class
public class ExcelImportController {

    private final ExcelImportService excelImportService;  // Service for handling Excel import logic

    /**
     * Endpoint to upload USSD request and response Excel files.
     *
     * @param requestFile The Excel file containing the request data.
     * @param responseFile The Excel file containing the response data.
     * @return A response entity indicating the result of the upload operation.
     * @throws Exception If any error occurs during file processing.
     */
    @PostMapping("/upload")  // Defines the POST method for file upload
    public ResponseEntity<String> uploadExcelData(
            @RequestParam("requestFile") MultipartFile requestFile,  // Request file from the user
            @RequestParam("responseFile") MultipartFile responseFile  // Response file from the user
    ) throws Exception {

        // Logging the file upload request with filenames
        log.info("Received Excel upload: requestFile={}, responseFile={}",
                requestFile.getOriginalFilename(), responseFile.getOriginalFilename());

        // Validate that both files are not empty
        if (requestFile.isEmpty() || responseFile.isEmpty()) {
            // Throwing an exception if either of the files is missing
            throw new IllegalArgumentException("Both requestFile and responseFile are required and cannot be empty.");
        }

        // Pass the files to the service layer for further processing (e.g., saving to the database)
        excelImportService.importExcelData(requestFile, responseFile);

        // Return a successful response with a message
        return ResponseEntity.ok("Excel data uploaded and processed successfully.");
    }
}
