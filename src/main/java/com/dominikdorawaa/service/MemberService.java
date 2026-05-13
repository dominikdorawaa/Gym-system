package com.dominikdorawaa.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dominikdorawaa.dto.MemberDto;
import com.dominikdorawaa.dto.MemberRegistrationDto;
import com.dominikdorawaa.entity.Member;
import com.dominikdorawaa.entity.MembershipPlan;
import com.dominikdorawaa.entity.MembershipStatus;
import com.dominikdorawaa.repository.MemberRepository;
import com.dominikdorawaa.repository.MembershipPlanRepository;

@Service
@Transactional
public class MemberService {

    private final MemberRepository memberRepository;
    private final MembershipPlanRepository membershipPlanRepository;

    public MemberService(MemberRepository memberRepository,
            MembershipPlanRepository membershipPlanRepository) {
        this.memberRepository = memberRepository;
        this.membershipPlanRepository = membershipPlanRepository;
    }

    public MemberDto registerMember(Long planId, MemberRegistrationDto request) {
        MembershipPlan plan = membershipPlanRepository.findById(planId)
                .orElseThrow(() -> new IllegalArgumentException("Plan not found"));

        long activeMembersCount = memberRepository.countByMembershipPlanIdAndStatus(planId,
                MembershipStatus.ACTIVE);

        if (activeMembersCount >= plan.getMaxMembers()) {
            throw new IllegalStateException("Plan capacity reached. Cannot add more members.");
        }

        Member member = new Member();
        member.setFirstName(request.firstName());
        member.setLastName(request.lastName());
        member.setEmail(request.email());
        member.setMembershipPlan(plan);

        Member savedMember = memberRepository.save(member);

        return mapToDto(savedMember);
    }

    @Transactional(readOnly = true)
    public List<MemberDto> getAllMembers() {
        return memberRepository.findAll().stream()
                .map(this::mapToDto)
                .toList();
    }

    public void cancelMembership(Long memberId) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new IllegalArgumentException("Member not found"));

        member.setStatus(MembershipStatus.CANCELLED);
        memberRepository.save(member);
    }

    private MemberDto mapToDto(Member member) {
        return new MemberDto(
                member.getId(),
                member.getFirstName(),
                member.getLastName(),
                member.getEmail(),
                member.getStatus(),
                member.getJoinDate(),
                member.getMembershipPlan().getName(),
                member.getMembershipPlan().getGym().getName());
    }
}
