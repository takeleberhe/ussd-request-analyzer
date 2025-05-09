package com.matrixtech.ussd.ussd_analyzer.dto;

import lombok.*;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FraudReportDTO {
    private String msisdn;
    private Set<String> voucherNumbers;
    private String reason; // e.g. "Multiple vouchers used"
}
