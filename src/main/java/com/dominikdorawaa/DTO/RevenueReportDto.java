package com.dominikdorawaa.dto;

import java.math.BigDecimal;

public record RevenueReportDto(
        String gymName,
        BigDecimal amount,
        String currency) {
}
