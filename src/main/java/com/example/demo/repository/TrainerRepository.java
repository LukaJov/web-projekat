package com.example.demo.repository;

import com.example.demo.model.Trainer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;


@Repository
public interface TrainerRepository extends  JpaRepository<Trainer, Long> {
    public List<Trainer> findByActive(boolean active);
}