package com.example.demo.repository;

import com.example.demo.model.Training;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;


@Repository
public interface TrainingRepository extends  JpaRepository<Training, Long> {
}