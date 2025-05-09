package com.matrixtech.ussd.ussd_analyzer.entity;

import jakarta.persistence.*;
import lombok.*;

// This entity maps to the `ussd_success_failure_view` SQL view.
@Entity
@Table(name = "ussd_success_failure_view")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SuccessFailureView {

    @Id
    @Column(name = "status")
    private String status; // 'success' or 'failure'

    @Column(name = "count")
    private Long count;    // Total number of requests for each status
}