package com.matrixtech.ussd.ussd_analyzer.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;


@Entity
@Table(name = "ussd_requests")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UssdRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // Automatically generate ID
    private Integer id;

    @Column(name = "correlation_id", nullable = false)
    private String correlationId;

    @Column(nullable = false)
    private String operation;

    @Column(name = "request_data")
    private String requestData;

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private LocalDateTime timestamp;
}
