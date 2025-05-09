package com.matrixtech.ussd.ussd_analyzer.controller;

import com.matrixtech.ussd.ussd_analyzer.repository.FraudBlacklistViewRepository;
import com.matrixtech.ussd.ussd_analyzer.repository.LatestRequestViewRepository;
import com.matrixtech.ussd.ussd_analyzer.repository.SuccessFailureViewRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/view")
@RequiredArgsConstructor
@Tag(name = "USSD View Reports", description = "Endpoints to fetch USSD-related reports and analytics")
public class ViewController {

    private final SuccessFailureViewRepository successRepo;
    private final LatestRequestViewRepository latestRepo;
    private final FraudBlacklistViewRepository fraudRepo;

    @GetMapping("/summary")
    @Operation(
            summary = "Get success/failure summary",
            description = "Returns all records summarizing USSD request successes and failures"
    )
    public List<?> getSuccessFailureReport() {
        return successRepo.findAll();
    }

    @GetMapping("/latest")
    @Operation(
            summary = "Get latest USSD requests",
            description = "Returns a limited number of recent USSD requests"
    )
    public List<?> getLatestRequests(
            @Parameter(description = "Number of latest requests to retrieve (default 10)")
            @RequestParam(defaultValue = "10") int limit
    ) {
        return latestRepo.findAll().stream().limit(limit).toList(); // Consider pageable for large data
    }

    @GetMapping("/fraud")
    @Operation(
            summary = "Get blacklisted MSISDNs",
            description = "Returns a list of MSISDNs flagged and blacklisted as fraudulent"
    )
    public List<?> getBlacklistedMsisdns() {
        return fraudRepo.findAll();
    }
}
