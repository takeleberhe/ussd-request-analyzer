package com.matrixtech.ussd.ussd_analyzer.service;

import com.matrixtech.ussd.ussd_analyzer.entity.UssdRequest;
import com.matrixtech.ussd.ussd_analyzer.entity.UssdResponse;
import com.matrixtech.ussd.ussd_analyzer.repository.UssdRequestRepository;
import com.matrixtech.ussd.ussd_analyzer.repository.UssdResponseRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Iterator;

/**
 * Service for importing USSD request and response data from Excel files.
 * The service reads data from two Excel files (request and response),
 * processes each row, and stores the data in respective database entities.
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class ExcelImportService {

    private final UssdRequestRepository requestRepo;
    private final UssdResponseRepository responseRepo;

    /**
     * Imports USSD request and response data from provided Excel files.
     * This method reads data from the request and response files, processes the data row by row,
     * and saves the processed data into the database.
     *
     * @param requestFile the Excel file containing USSD request data
     * @param responseFile the Excel file containing USSD response data
     * @throws Exception if any error occurs during the file processing
     */
    public void importExcelData(MultipartFile requestFile, MultipartFile responseFile) throws Exception {
        try (
                InputStream reqInputStream = requestFile.getInputStream();
                InputStream resInputStream = responseFile.getInputStream();
                Workbook reqWorkbook = new XSSFWorkbook(reqInputStream);
                Workbook resWorkbook = new XSSFWorkbook(resInputStream)
        ) {
            // Get the first sheet from both workbooks (assuming single sheet per file)
            Sheet reqSheet = reqWorkbook.getSheetAt(0);
            Sheet resSheet = resWorkbook.getSheetAt(0);

            // Create iterators to loop through each row in the sheets
            Iterator<Row> reqRows = reqSheet.iterator();
            Iterator<Row> resRows = resSheet.iterator();

            // Skip the header rows
            if (reqRows.hasNext()) reqRows.next();
            if (resRows.hasNext()) resRows.next();

            int rowIndex = 1; // Start row index for processing

            // Loop through the rows in both sheets
            while (reqRows.hasNext() && resRows.hasNext()) {
                Row reqRow = reqRows.next();
                Row resRow = resRows.next();

                try {
                    // Parse fields from the request row
                    String correlationId = getCellString(reqRow, 1);
                    String operationReq = getCellString(reqRow, 2);
                    String requestData = getCellString(reqRow, 3);
                    LocalDateTime timestampReq = getCellTimestamp(reqRow, 4);

                    // Skip empty rows
                    if (correlationId.isEmpty() && operationReq.isEmpty() && requestData.isEmpty()) {
                        log.warn("Skipping empty request row {}", rowIndex);
                        continue; // Continue to the next row if current one is empty
                    }

                    // Create and save UssdRequest entity
                    UssdRequest request = new UssdRequest();
                    request.setCorrelationId(correlationId.isEmpty() ? null : correlationId);
                    request.setOperation(operationReq);
                    request.setRequestData(requestData);
                    request.setTimestamp(timestampReq != null ? timestampReq : LocalDateTime.now());
                    requestRepo.save(request); // Save request directly to the repository

                    // Parse fields from the response row
                    String operationRes = getCellString(resRow, 2);
                    String status = getCellString(resRow, 3);
                    String reply = getCellString(resRow, 4);
                    LocalDateTime timestampRes = getCellTimestamp(resRow, 5);

                    // Create and save UssdResponse entity
                    UssdResponse response = new UssdResponse();
                    response.setCorrelationId(correlationId.isEmpty() ? null : correlationId);
                    response.setOperation(operationRes);
                    response.setStatus(status);
                    response.setReply(reply);
                    response.setTimestamp(timestampRes != null ? timestampRes : LocalDateTime.now());
                    responseRepo.save(response); // Save response directly to the repository

                } catch (Exception ex) {
                    // Log and skip rows with errors to prevent crashing the entire process
                    log.error("Failed to process row {}: {}", rowIndex, ex.getMessage(), ex);
                }
                rowIndex++; // Increment the row index
            }

        } catch (Exception e) {
            // Log and throw exception if something goes wrong during the import process
            log.error("Failed to import Excel data", e);
            throw new Exception("Failed to import Excel data: " + e.getMessage(), e);
        }
    }

    /**
     * Extracts a string value from a cell.
     * This method ensures that blank cells are safely handled by returning an empty string.
     *
     * @param row the Excel row containing the cell
     * @param index the column index of the cell to retrieve
     * @return the string value of the cell
     */
    private String getCellString(Row row, int index) {
        Cell cell = row.getCell(index, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
        return cell.toString().trim(); // Ensure blank cells are safely handled
    }

    /**
     * Extracts a timestamp value from a cell.
     * If the cell contains a valid date, it's converted to a LocalDateTime object.
     * If invalid, it returns null and logs a warning.
     *
     * @param row the Excel row containing the cell
     * @param index the column index of the cell to retrieve
     * @return the timestamp as a LocalDateTime, or null if invalid
     */
    private LocalDateTime getCellTimestamp(Row row, int index) {
        try {
            Cell cell = row.getCell(index, Row.MissingCellPolicy.RETURN_BLANK_AS_NULL);
            if (cell != null && cell.getCellType() == CellType.NUMERIC && DateUtil.isCellDateFormatted(cell)) {
                return LocalDateTime.ofInstant(cell.getDateCellValue().toInstant(), ZoneId.systemDefault());
            }
        } catch (Exception e) {
            // Log warning for invalid timestamp in the row
            log.warn("Invalid timestamp in row {} col {}: {}", row.getRowNum(), index, e.getMessage());
        }
        return null; // Return null if the timestamp is invalid
    }
}
