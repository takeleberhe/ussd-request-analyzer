package com.matrixtech.ussd.ussd_analyzer.repository;

import com.matrixtech.ussd.ussd_analyzer.entity.FraudBlacklistView;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FraudCandidateRepository extends JpaRepository<FraudBlacklistView, String> {}
