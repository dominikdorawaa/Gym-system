package com.dominikdorawaa.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dominikdorawaa.dto.MemberDto;
import com.dominikdorawaa.dto.MemberRegistrationDto;
import com.dominikdorawaa.service.MemberService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api")
public class MemberController {

    private final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @PostMapping("/plans/{planId}/members")
    public ResponseEntity<MemberDto> registerMember(
            @PathVariable Long planId,
            @Valid @RequestBody MemberRegistrationDto request) {
        MemberDto createdMember = memberService.registerMember(planId, request);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdMember);
    }

    @GetMapping("/members")
    public ResponseEntity<List<MemberDto>> getAllMembers() {
        return ResponseEntity.ok(memberService.getAllMembers());
    }

    @PutMapping("/members/{memberId}/cancel")
    public ResponseEntity<Void> cancelMembership(@PathVariable Long memberId) {
        memberService.cancelMembership(memberId);
        return ResponseEntity.noContent().build();
    }
}
