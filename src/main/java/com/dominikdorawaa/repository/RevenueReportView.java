package com.dominikdorawaa.repository;

import java.math.BigDecimal;

public interface RevenueReportView {

    String getGymName();

    BigDecimal getAmount();

    String getCurrency();
}
