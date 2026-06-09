package com.dominikdorawaa.controller;


import com.dominikdorawaa.dto.RevenueReportDto;
import com.dominikdorawaa.service.RevenueReportService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/reports")
public class RevenueReportController {

    private final RevenueReportService revenueReportService;

    public RevenueReportController(RevenueReportService revenueReportService) {
        this.revenueReportService = revenueReportService;
    }

    @GetMapping("/revenue")
    public ResponseEntity<List<RevenueReportDto>> getRevenueReport() {
        return ResponseEntity.ok(revenueReportService.getRevenueReport());
    }

}
