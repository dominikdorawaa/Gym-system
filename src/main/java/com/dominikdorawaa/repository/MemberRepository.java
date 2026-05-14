package com.dominikdorawaa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.dominikdorawaa.entity.Member;
import com.dominikdorawaa.entity.MembershipStatus;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {

    long countByMembershipPlanIdAndStatus(Long planId, MembershipStatus status);

    @Query("SELECT g.name AS gymName, SUM(mp.monthlyPrice) AS amount, mp.currency AS currency " +
            "FROM Member m " +
            "JOIN m.membershipPlan mp " +
            "JOIN mp.gym g " +
            "WHERE m.status = 'ACTIVE' " +
            "GROUP BY g.name, mp.currency")
    List<RevenueReportView> getRevenueReport();

}
