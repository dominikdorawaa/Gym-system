package com.dominikdorawaa.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dominikdorawaa.dto.MembershipPlanDto;
import com.dominikdorawaa.service.MembershipPlanService;

@RestController
@RequestMapping("/api/gyms/{gymId}/membership-plans")
public class MembershipPlanController {

    private final MembershipPlanService membershipPlanService;

    public MembershipPlanController(MembershipPlanService membershipPlanService) {
        this.membershipPlanService = membershipPlanService;
    }

    @PostMapping
    public ResponseEntity<MembershipPlanDto> createMembershipPlan(
            @PathVariable Long gymId,
            @RequestBody MembershipPlanDto request) {
        MembershipPlanDto createdPlan = membershipPlanService.createMembershipPlan(gymId, request);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdPlan);
    }

    @GetMapping
    public ResponseEntity<List<MembershipPlanDto>> getMembershipPlans(@PathVariable Long gymId) {
        List<MembershipPlanDto> plans = membershipPlanService.getPlansByGymId(gymId);
        return ResponseEntity.ok(plans);
    }
}
