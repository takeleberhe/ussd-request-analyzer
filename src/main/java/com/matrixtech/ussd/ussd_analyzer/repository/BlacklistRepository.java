package com.matrixtech.ussd.ussd_analyzer.repository;

import com.matrixtech.ussd.ussd_analyzer.entity.BlacklistEntry;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BlacklistRepository extends JpaRepository<BlacklistEntry, Long> {

    // Check if MSISDN is already blacklisted
    boolean existsByMsisdn(String msisdn);

    // Retrieve blacklist entry by MSISDN
    Optional<BlacklistEntry> findByMsisdn(String msisdn);

}
