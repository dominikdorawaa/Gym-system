package com.dominikdorawaa.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.dominikdorawaa.dto.MemberDto;
import com.dominikdorawaa.dto.MemberRegistrationDto;
import com.dominikdorawaa.entity.Gym;
import com.dominikdorawaa.entity.Member;
import com.dominikdorawaa.entity.MembershipPlan;
import com.dominikdorawaa.entity.MembershipStatus;
import com.dominikdorawaa.repository.MemberRepository;
import com.dominikdorawaa.repository.MembershipPlanRepository;

@ExtendWith(MockitoExtension.class)
class MemberServiceTest {

    @Mock
    private MemberRepository memberRepository;

    @Mock
    private MembershipPlanRepository membershipPlanRepository;

    @InjectMocks
    private MemberService memberService;

    @Test
    void shouldRegisterMemberWhenCapacityNotReached() {

        Gym gym = new Gym();
        gym.setName("FitLife");
        MembershipPlan plan = new MembershipPlan();
        plan.setId(1L);
        plan.setMaxMembers(10);
        plan.setName("Basic");
        plan.setGym(gym);
        MemberRegistrationDto request = new MemberRegistrationDto("Jan", "Kowalski", "jan@example.com");
        when(membershipPlanRepository.findById(1L)).thenReturn(Optional.of(plan));
        when(memberRepository.countByMembershipPlanIdAndStatus(1L, MembershipStatus.ACTIVE)).thenReturn(5L);
        Member savedMember = new Member();
        savedMember.setFirstName("Jan");
        savedMember.setLastName("Kowalski");
        savedMember.setMembershipPlan(plan);
        savedMember.setStatus(MembershipStatus.ACTIVE);
        when(memberRepository.save(any(Member.class))).thenReturn(savedMember);
        MemberDto result = memberService.registerMember(1L, request);
        assertNotNull(result);
        assertEquals("Jan", result.firstName());
        assertEquals(MembershipStatus.ACTIVE, result.status());
    }

    @Test
    void shouldCancelMembership() {
        Member member = new Member();
        member.setId(1L);
        member.setStatus(MembershipStatus.ACTIVE);
        when(memberRepository.findById(1L)).thenReturn(Optional.of(member));
        memberService.cancelMembership(1L);
        assertEquals(MembershipStatus.CANCELLED, member.getStatus());
    }

}
