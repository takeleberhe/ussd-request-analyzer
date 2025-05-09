package com.matrixtech.ussd.ussd_analyzer.dto;

import lombok.*;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UssdResponseDTO {
    private Integer id;
    private String correlationId;
    private String operation;
    private String status;
    private String reply;
    private LocalDateTime timestamp;
}
