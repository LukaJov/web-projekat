package com.example.demo.repository;

import com.example.demo.model.Trainer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Repository
public interface TrainerRepository extends  JpaRepository<Trainer, Long> {
    public List<Trainer> findByActive(boolean active);
    public Optional<Trainer> findById(Long id);
    public Trainer findByUsername(String username);
}