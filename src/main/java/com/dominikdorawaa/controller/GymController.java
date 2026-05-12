package com.dominikdorawaa.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dominikdorawaa.DTO.GymDto;
import com.dominikdorawaa.service.GymService;

@RestController
@RequestMapping("/api/gyms")
public class GymController {

    private final GymService gymService;

    public GymController(GymService gymService) {
        this.gymService = gymService;
    }

    @PostMapping
    public GymDto createGym(@RequestBody GymDto request) {
        return gymService.createGym(request);
    }

    @GetMapping
    public List<GymDto> getAllGyms() {
        return gymService.getAllGyms();
    }

}
