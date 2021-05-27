package com.example.demo.repository;

import com.example.demo.model.FitnessCenter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FitnessCenterRepository extends JpaRepository<FitnessCenter, Long> {
}
