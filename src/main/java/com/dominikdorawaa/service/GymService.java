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
        return toDto(savedGym);
    }

    public List<GymDto> getAllGyms() {
        return gymRepository.findAll().stream()
                .map(gym -> toDto(gym))
                .toList();
    }

    private GymDto toDto(Gym gym) {
        return new GymDto(
                gym.getId(),
                gym.getName(),
                gym.getAddress(),
                gym.getPhoneNumber()
        );
    }

}
