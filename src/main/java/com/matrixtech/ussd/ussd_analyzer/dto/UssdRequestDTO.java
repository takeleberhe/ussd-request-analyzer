package com.matrixtech.ussd.ussd_analyzer.dto;

import lombok.*;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UssdRequestDTO {
    private Integer id;
    private String correlationId;
    private String operation;
    private String requestData;
    private LocalDateTime timestamp;
}
