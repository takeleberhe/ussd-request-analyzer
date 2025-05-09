package com.matrixtech.ussd.ussd_analyzer.service;

import com.matrixtech.ussd.ussd_analyzer.entity.BlacklistEntry;
import com.matrixtech.ussd.ussd_analyzer.repository.BlacklistRepository;
import com.matrixtech.ussd.ussd_analyzer.repository.FraudCandidateRepository;
import com.matrixtech.ussd.ussd_analyzer.entity.FraudBlacklistView;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class FraudDetectionService {

    private final BlacklistRepository blacklistRepository;
    private final FraudCandidateRepository fraudCandidateRepository;

    @Scheduled(cron = "0 */10 * * * *") // every 10 minutes
    public void detectFraudFromView() {
        log.info("Checking fraud view...");

        List<FraudBlacklistView> candidates = fraudCandidateRepository.findAll();

        for (FraudBlacklistView candidate : candidates) {
            String msisdn = candidate.getMsisdn();

            if (!blacklistRepository.existsByMsisdn(msisdn)) {
                BlacklistEntry entry = BlacklistEntry.builder()
                        .msisdn(msisdn)
                        .reason("Detected from fraud_view: " + candidate.getDistinctVouchers() + " vouchers")
                        .createdAt(LocalDateTime.now())
                        .build();

                blacklistRepository.save(entry);
                log.warn("Blacklisted: {}", msisdn);
            }
        }

        log.info("Fraud check complete. {} entries processed.", candidates.size());
    }
}
