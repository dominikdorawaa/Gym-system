package com.dominikdorawaa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.entity.Gym;

@Repository
public interface GymRepository extends JpaRepository<Gym, Long> {

}
