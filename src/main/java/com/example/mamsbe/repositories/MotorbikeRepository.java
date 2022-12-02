package com.example.mamsbe.repositories;

import com.example.mamsbe.models.Motorbike;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MotorbikeRepository extends JpaRepository<Motorbike, Long> {
}
