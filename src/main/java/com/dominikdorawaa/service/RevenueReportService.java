package com.dominikdorawaa.service;


import com.dominikdorawaa.dto.RevenueReportDto;
import com.dominikdorawaa.repository.MemberRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RevenueReportService {


    private final MemberRepository memberRepository;


    public RevenueReportService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }


    public List<RevenueReportDto> getRevenueReport() {
        return memberRepository.getRevenueReport().stream()
                .map(view -> new RevenueReportDto(
                        view.getGymName(),
                        view.getAmount(),
                        view.getCurrency()
                ))
                .toList();
    }


}
