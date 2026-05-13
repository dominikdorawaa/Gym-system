package com.dominikdorawaa.dto;

import java.time.LocalDate;
import com.dominikdorawaa.entity.MembershipStatus;

public record MemberDto(
        Long id, 
        String firstName, 
        String lastName, 
        String email, 
        MembershipStatus status,
        LocalDate joinDate, 
        String planName, 
        String gymName) {
}
