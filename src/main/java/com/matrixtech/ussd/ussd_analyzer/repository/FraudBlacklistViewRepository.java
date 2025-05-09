package com.matrixtech.ussd.ussd_analyzer.repository;


import com.matrixtech.ussd.ussd_analyzer.entity.FraudBlacklistView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FraudBlacklistViewRepository extends JpaRepository<FraudBlacklistView, String> {
}

