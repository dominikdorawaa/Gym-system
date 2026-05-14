package com.dominikdorawaa.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.dominikdorawaa.dto.GymDto;
import com.dominikdorawaa.dto.RevenueReportDto;
import com.dominikdorawaa.entity.Gym;
import com.dominikdorawaa.repository.GymRepository;
import com.dominikdorawaa.repository.MemberRepository;

@Service
public class GymService {

    private final GymRepository gymRepository;
    private final MemberRepository memberRepository;

    public GymService(GymRepository gymRepository, MemberRepository memberRepository) {
        this.gymRepository = gymRepository;
        this.memberRepository = memberRepository;
    }

    public GymDto createGym(GymDto request) {
        Gym gym = new Gym();
        gym.setName(request.name());
        gym.setAddress(request.address());
        gym.setPhoneNumber(request.phoneNumber());
        Gym savedGym = gymRepository.save(gym);
        return new GymDto(savedGym.getId(), savedGym.getName(), savedGym.getAddress(), savedGym.getPhoneNumber());
    }

    public List<GymDto> getAllGyms() {
        List<Gym> gyms = gymRepository.findAll();
        return gyms.stream()
                .map(gym -> new GymDto(gym.getId(), gym.getName(), gym.getAddress(), gym.getPhoneNumber()))
                .toList();
    }

    public List<RevenueReportDto> getRevenueReport() {
        return memberRepository.getRevenueReport().stream()
                .map(view -> new RevenueReportDto(view.getGymName(), view.getAmount(), view.getCurrency()))
                .toList();
    }

}
