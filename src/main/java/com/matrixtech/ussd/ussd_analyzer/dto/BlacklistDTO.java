package com.matrixtech.ussd.ussd_analyzer.dto;

import lombok.*;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BlacklistDTO {
    private Long id;
    private String msisdn;
    private String reason;
    private LocalDateTime createdAt;
}
