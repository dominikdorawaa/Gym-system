package com.dominikdorawaa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dominikdorawaa.entity.Member;
import com.dominikdorawaa.entity.MembershipStatus;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {

    long countByMembershipPlanIdAndStatus(Long planId, MembershipStatus status);

}
