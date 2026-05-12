package com.dominikdorawaa.dto;

import java.math.BigDecimal;

import com.dominikdorawaa.entity.MembershipPlanType;

public record MembershipPlanDto(Long id, String name, int durationInMonths, BigDecimal monthlyPrice, 
    int maxMembers, String currency, MembershipPlanType planType, Long gymId) {

}
