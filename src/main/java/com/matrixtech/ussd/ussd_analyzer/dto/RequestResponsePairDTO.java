package com.matrixtech.ussd.ussd_analyzer.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RequestResponsePairDTO {
    private UssdRequestDTO request;
    private UssdResponseDTO response;
}
