package com.matrixtech.ussd.ussd_analyzer.repository;

import com.matrixtech.ussd.ussd_analyzer.entity.UssdResponse;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UssdResponseRepository extends JpaRepository<UssdResponse, Integer> {

    // Get response by correlation ID (for pairing with request)
    Optional<UssdResponse> findByCorrelationId(String correlationId);

    // Get all successful responses
    List<UssdResponse> findByStatus(String status);

    // Optional: for success/failure report
    long countByStatus(String status);

    // You could also query recent responses if needed
    List<UssdResponse> findTop10ByOrderByTimestampDesc();
}
