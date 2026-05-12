package com.dominikdorawaa.service;

import org.springframework.stereotype.Service;
import com.dominikdorawaa.entity.MembershipPlanType;
import com.dominikdorawaa.dto.MembershipPlanDto;
import com.dominikdorawaa.entity.Gym;
import com.dominikdorawaa.entity.MembershipPlan;
import java.util.List;
import com.dominikdorawaa.repository.GymRepository;
import com.dominikdorawaa.repository.MembershipPlanRepository;

@Service
public class MembershipPlanService {

    private final MembershipPlanRepository membershipPlanRepository;

    private final GymRepository gymRepository;

    public MembershipPlanService(MembershipPlanRepository membershipPlanRepository, GymRepository gymRepository) {
        this.membershipPlanRepository = membershipPlanRepository;
        this.gymRepository = gymRepository;
    }

    public MembershipPlanDto createMembershipPlan(Long gymId, MembershipPlanDto request) {
        Gym gym = gymRepository.findById(gymId)
                .orElseThrow(() -> new RuntimeException("Gym not found"));
        MembershipPlan membershipPlan = new MembershipPlan();
        membershipPlan.setGym(gym);
        membershipPlan.setName(request.name());
        membershipPlan.setDurationInMonths(request.durationInMonths());
        membershipPlan.setMonthlyPrice(request.monthlyPrice());
        membershipPlan.setMaxMembers(request.maxMembers());
        membershipPlan.setCurrency(request.currency());
        membershipPlan.setPlanType(request.planType());
        MembershipPlan savedMembershipPlan = membershipPlanRepository.save(membershipPlan);
        return new MembershipPlanDto(savedMembershipPlan.getId(), savedMembershipPlan.getName(),
                savedMembershipPlan.getDurationInMonths(), savedMembershipPlan.getMonthlyPrice(),
                savedMembershipPlan.getMaxMembers(), savedMembershipPlan.getCurrency(),
                savedMembershipPlan.getPlanType(), savedMembershipPlan.getGym().getId());
    }

        public List<MembershipPlanDto> getPlansByGymId(Long gymId) {
            List<MembershipPlan> membershipPlans = membershipPlanRepository.findByGymId(gymId);
            return membershipPlans.stream()
                    .map(plan -> new MembershipPlanDto(plan.getId(), plan.getName(), plan.getDurationInMonths(),
                            plan.getMonthlyPrice(), plan.getMaxMembers(), plan.getCurrency(), plan.getPlanType(),
                            plan.getGym().getId()))
                    .toList();
        }

}
