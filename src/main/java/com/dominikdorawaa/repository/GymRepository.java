package com.dominikdorawaa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dominikdorawaa.entity.Gym;

@Repository
public interface GymRepository extends JpaRepository<Gym, Long> {

}