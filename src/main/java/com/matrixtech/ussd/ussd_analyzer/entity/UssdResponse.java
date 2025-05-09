package com.matrixtech.ussd.ussd_analyzer.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "ussd_responses")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UssdResponse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-generate the ID for the entity
    private Integer id;

    @Column(name = "correlation_id", nullable = false)
    private String correlationId;

    @Column(nullable = false)
    private String operation;

    @Column(nullable = false)
    private String status;

    @Column(nullable = false)
    private String reply;

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private LocalDateTime timestamp;

    @UpdateTimestamp
    @Column(nullable = true)  // Optional field to track the update timestamp (if needed)
    private LocalDateTime updatedTimestamp;
}
