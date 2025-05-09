package com.matrixtech.ussd.ussd_analyzer.repository;

import com.matrixtech.ussd.ussd_analyzer.entity.UssdRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UssdRequestRepository extends JpaRepository<UssdRequest, Long> {

}

