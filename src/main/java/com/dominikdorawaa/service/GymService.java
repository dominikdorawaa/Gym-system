package com.dominikdorawaa.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.dominikdorawaa.dto.GymDto;
import com.dominikdorawaa.entity.Gym;
import com.dominikdorawaa.repository.GymRepository;

@Service
public class GymService {

    private final GymRepository gymRepository;

    public GymService(GymRepository gymRepository) {
        this.gymRepository = gymRepository;
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

}
