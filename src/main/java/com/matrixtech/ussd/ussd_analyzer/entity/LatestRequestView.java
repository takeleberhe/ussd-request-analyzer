package com.matrixtech.ussd.ussd_analyzer.entity;

import jakarta.persistence.*;
import lombok.*;

// This entity maps to the `latest_requests_view` SQL view.
@Entity
@Table(name = "latest_requests_view")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LatestRequestView {

    @Id
    @Column(name = "request_id")
    private Long requestId;

    private String correlationId;
    private String operation;

    @Column(name = "request_data")
    private String requestData;

    @Column(name = "request_time")
    private String requestTime;

    private String status;
    private String reply;

    @Column(name = "response_time")
    private String responseTime;
}
