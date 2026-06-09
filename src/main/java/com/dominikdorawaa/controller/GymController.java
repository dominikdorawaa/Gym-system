package com.dominikdorawaa.controller;

import java.util.List;

import com.dominikdorawaa.service.RevenueReportService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dominikdorawaa.dto.GymDto;
import com.dominikdorawaa.dto.RevenueReportDto;
import com.dominikdorawaa.service.GymService;

@RestController
@RequestMapping("/api/gyms")
public class GymController {

    private final GymService gymService;

    public GymController(GymService gymService) {
        this.gymService = gymService;
    }

    @PostMapping
    public ResponseEntity<GymDto> createGym(@RequestBody GymDto request) {
        GymDto created = gymService.createGym(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @GetMapping
    public ResponseEntity<List<GymDto>> getAllGyms() {
        return ResponseEntity.ok(gymService.getAllGyms());
    }



}
